package com.pial.gym.gymapi.service.impl;

import com.pial.gym.gymapi.dto.model.Membership;
import com.pial.gym.gymapi.dto.request.MembershipCreateRequest;
import com.pial.gym.gymapi.dto.request.MembershipGetAllByFilterRequest;
import com.pial.gym.gymapi.entity.*;
import com.pial.gym.gymapi.enumerable.MembershipStatusEnum;
import com.pial.gym.gymapi.enumerable.PromotionDurationTypeEnum;
import com.pial.gym.gymapi.repository.*;
import com.pial.gym.gymapi.service.IMembershipService;
import com.pial.gym.gymapi.specification.MembershipSpecification;
import com.pial.gym.gymapi.utils.DateUtils;
import com.pial.gym.gymapi.utils.ModelConvertionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MembershipService implements IMembershipService {
    @Autowired
    private IMembershipRepository iMembershipRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private ICompanyRepository iCompanyRepository;
    @Autowired
    private IMembershipPeriodRepository iMembershipPeriodRepository;
    @Autowired
    private IPromotionRepository iPromotionRepository;
    @Autowired
    private IMembershipUserRelationRepository iMembershipUserRelationRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private DateUtils dateUtils;

    @Override
    public String create(MembershipCreateRequest request) {
        String message = "Membership successfully created.";
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserEntity userRegisterEntity = null;
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
                String currentUsername = userDetails.getUsername();
                userRegisterEntity = iUserRepository.findByUsername(currentUsername).orElse(null);
            }
            if (userRegisterEntity == null) {
                return "Register user must be logged in.";
            }
            MembershipEntity membershipEntity = iMembershipRepository.findByUserClientIdAndCompanyId(request.getUserClientId()
                    , request.getCompanyId()).orElse(null);
            if (membershipEntity != null) {
                if (membershipEntity.getStatus().equals(MembershipStatusEnum.ACTIVE))
                    return "User already has an active membership.";
                if (membershipEntity.getStatus().equals(MembershipStatusEnum.ON_PAUSE))
                    return "User already has a membership on pause.";
                if (membershipEntity.getStatus().equals(MembershipStatusEnum.INACTIVE))
                    return "User already has an inactive membership.";
            }
            UserEntity userClientEntity = iUserRepository.findById(request.getUserClientId()).orElseThrow(() ->
                    new Exception(messageSource.getMessage("msg.user.find.notfound", null, LocaleContextHolder.getLocale())));
            CompanyEntity companyEntity = iCompanyRepository.findById(request.getCompanyId()).orElseThrow(() ->
                    new Exception(messageSource.getMessage("msg.company.find.notfound", null ,LocaleContextHolder.getLocale())));
            MembershipEntity newMembershipEntity = new MembershipEntity();
            newMembershipEntity.setUserRegister(userRegisterEntity);
            newMembershipEntity.setUserClient(userClientEntity);
            newMembershipEntity.setCompany(companyEntity);
            newMembershipEntity.setStatus(MembershipStatusEnum.valueOf(request.getStatus()));
            newMembershipEntity.setCreationDate(LocalDate.now());
            newMembershipEntity.setModificationDate(LocalDate.now());
            iMembershipRepository.saveAndFlush(newMembershipEntity);

            PromotionEntity promotionEntity = iPromotionRepository.findById(request.getPromotionId()).orElseThrow(() ->
                    new Exception(messageSource.getMessage("msg.promotion.find.notfound", null, LocaleContextHolder.getLocale())));

            LocalDate startDate = dateUtils.convertStringToLocalDate(request.getStartPeriodDate());
            LocalDate endDate = LocalDate.now();
            if (promotionEntity.getDurationType().equals(PromotionDurationTypeEnum.DAY)) {
                endDate = startDate.plusDays(promotionEntity.getDuration());
            }
            if (promotionEntity.getDurationType().equals(PromotionDurationTypeEnum.MONTH)) {
                endDate = startDate.plusMonths(promotionEntity.getDuration());
            }
            if (promotionEntity.getDurationType().equals(PromotionDurationTypeEnum.YEAR)) {
                endDate = startDate.plusYears(promotionEntity.getDuration());
            }

            MembershipPeriodEntity newMembershipPeriodEntity = new MembershipPeriodEntity();
            newMembershipPeriodEntity.setMembership(newMembershipEntity);
            newMembershipPeriodEntity.setPromotion(promotionEntity);
            newMembershipPeriodEntity.setStartDate(startDate);
            newMembershipPeriodEntity.setEndDate(endDate);
            newMembershipPeriodEntity.setCreationDate(LocalDate.now());
            newMembershipPeriodEntity.setModificationDate(LocalDate.now());
            iMembershipPeriodRepository.saveAndFlush(newMembershipPeriodEntity);

            if (!request.isUserRelationPromotion()) {
                return message;
            }

            List<UserEntity> userEntityPromotionRelationList = iUserRepository.findAllById(request.getUserClientRelationIdList());
            if (userEntityPromotionRelationList.isEmpty()) {
                return "Not users found";
            }

            List<MembershipUserRelationEntity> membershipUserRelationEntityList = new ArrayList<>();
            userEntityPromotionRelationList.forEach(userEntity -> {
                MembershipUserRelationEntity newMembershipUserRelationEntity = new MembershipUserRelationEntity();
                newMembershipUserRelationEntity.setMembershipPeriod(newMembershipPeriodEntity);
                newMembershipUserRelationEntity.setUserClient(userEntity);
                membershipUserRelationEntityList.add(newMembershipUserRelationEntity);
            });
            iMembershipUserRelationRepository.saveAllAndFlush(membershipUserRelationEntityList);
        } catch (Exception e) {
            log.error("Error create membership: {}", e.getMessage());
        }
        return message;
    }

    @Override
    public Slice<Membership> getAllByFilter(MembershipGetAllByFilterRequest request) {
        Specification<MembershipEntity> specification = getSpecificationFilter(request);

        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Slice<MembershipEntity> membershipEntitySlice = iMembershipRepository.findAll(specification, pageable);
        List<Membership> membershipList = new ArrayList<>();
        membershipEntitySlice.forEach(membershipEntity -> {
            membershipList.add(ModelConvertionUtils.getMembership(membershipEntity));
        });

        boolean hasNext = membershipList.size() > pageable.getPageSize();
        List<Membership> sliceContent = hasNext ? membershipList.subList(0, pageable.getPageSize()) : membershipList;

        return new SliceImpl<>(sliceContent, pageable, hasNext);
    }

    private Specification<MembershipEntity> getSpecificationFilter(MembershipGetAllByFilterRequest request) {
        Specification<MembershipEntity> specification = Specification.unrestricted();
        if (request.getUserNameRegister() != null) {
            specification = specification.and(MembershipSpecification.findByUserNameRegister(request.getUserNameRegister()));
        }
        if (request.getUserNameClient() != null) {
            specification = specification.and(MembershipSpecification.findByUserNameClient(request.getUserNameClient()));
        }
        if (request.getCompanyId() != null) {
            specification = specification.and(MembershipSpecification.findByCompany(request.getCompanyId()));
        }
        if (request.getStatus() != null) {
            specification = specification.and(MembershipSpecification.findByStatus(request.getStatus()));
        }
        return specification;
    }
}
