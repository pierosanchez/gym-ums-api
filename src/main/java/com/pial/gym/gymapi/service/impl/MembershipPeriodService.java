package com.pial.gym.gymapi.service.impl;

import com.pial.gym.gymapi.dto.model.MembershipPeriod;
import com.pial.gym.gymapi.dto.request.MembershipPeriodCreateRequest;
import com.pial.gym.gymapi.dto.request.MembershipPeriodGetAllByFilter;
import com.pial.gym.gymapi.dto.request.MembershipPeriodUpdateRequest;
import com.pial.gym.gymapi.entity.*;
import com.pial.gym.gymapi.enumerable.PromotionDurationTypeEnum;
import com.pial.gym.gymapi.repository.*;
import com.pial.gym.gymapi.service.IMembershipPeriodService;
import com.pial.gym.gymapi.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MembershipPeriodService implements IMembershipPeriodService {
    @Autowired
    private IMembershipPeriodRepository iMembershipPeriodRepository;
    @Autowired
    private IMembershipRepository iMembershipRepository;
    @Autowired
    private IMembershipUserRelationRepository iMembershipUserRelationRepository;
    @Autowired
    private IPromotionRepository iPromotionRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private DateUtils dateUtils;

    @Override
    public String create(MembershipPeriodCreateRequest request) {
        String message = "Membership period successfully created";
        try {
            MembershipEntity membershipEntity = iMembershipRepository.findById(request.getMembershipId()).orElseThrow(() ->
                    new Exception("Membership not found"));
            PromotionEntity promotionEntity = iPromotionRepository.findById(request.getPromotionId()).orElseThrow(() ->
                    new Exception("Promotion not found"));

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

            MembershipPeriodEntity membershipPeriodEntity = new MembershipPeriodEntity();
            membershipPeriodEntity.setMembership(membershipEntity);
            membershipPeriodEntity.setPromotion(promotionEntity);
            membershipPeriodEntity.setStartDate(startDate);
            membershipPeriodEntity.setEndDate(endDate);
            membershipPeriodEntity.setCreationDate(LocalDate.now());
            membershipPeriodEntity.setModificationDate(LocalDate.now());
            iMembershipPeriodRepository.saveAndFlush(membershipPeriodEntity);

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
                newMembershipUserRelationEntity.setMembershipPeriod(membershipPeriodEntity);
                newMembershipUserRelationEntity.setUserClient(userEntity);
                membershipUserRelationEntityList.add(newMembershipUserRelationEntity);
            });
            iMembershipUserRelationRepository.saveAllAndFlush(membershipUserRelationEntityList);
        } catch (Exception e) {
            log.error("Error create membership period: {}", e.getMessage());
        }
        return message;
    }

    @Override
    public String update(MembershipPeriodUpdateRequest request) {
        return "";
    }

    @Override
    public Slice<MembershipPeriod> getAllByFilter(MembershipPeriodGetAllByFilter request) {
        return null;
    }
}
