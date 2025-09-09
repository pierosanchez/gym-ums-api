package com.pial.gym.gymapi.service.impl;

import com.pial.gym.gymapi.dto.model.Promotion;
import com.pial.gym.gymapi.dto.request.PromotionCreateRequest;
import com.pial.gym.gymapi.dto.request.PromotionGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.PromotionUpdateRequest;
import com.pial.gym.gymapi.entity.CompanyEntity;
import com.pial.gym.gymapi.entity.PromotionEntity;
import com.pial.gym.gymapi.entity.PromotionTypeEntity;
import com.pial.gym.gymapi.repository.ICompanyRepository;
import com.pial.gym.gymapi.repository.IPromotionRepository;
import com.pial.gym.gymapi.repository.IPromotionTypeRepository;
import com.pial.gym.gymapi.service.IPromotionService;
import com.pial.gym.gymapi.specification.PromotionSpecification;
import com.pial.gym.gymapi.utils.DateUtils;
import com.pial.gym.gymapi.utils.ModelConvertionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private IPromotionTypeRepository iPromotionTypeRepository;
    @Autowired
    private IPromotionRepository iPromotionRepository;
    @Autowired
    private ICompanyRepository iCompanyRepository;
    @Autowired
    private DateUtils dateUtils;

    @Override
    public String create(PromotionCreateRequest request) {
        String message = "Promotion successfully created.";
        try {
            CompanyEntity companyEntity = iCompanyRepository.findById(request.getCompanyId()).orElseThrow(() -> new Exception("Company not found"));
            PromotionTypeEntity promotionTypeEntity = iPromotionTypeRepository.findById(request.getPromotionTypeId()).orElseThrow(() -> new Exception("Promotion Type not found"));
            PromotionEntity promotionEntity = new PromotionEntity();
            promotionEntity.setTitle(request.getTitle());
            promotionEntity.setDuration(request.getDuration());
            promotionEntity.setDurationType(request.getDurationType());
            promotionEntity.setPromotionType(promotionTypeEntity);
            promotionEntity.setCompany(companyEntity);
            promotionEntity.setStatus(request.getStatus());
            promotionEntity.setPrice(request.getPrice());
            promotionEntity.setStartDate(dateUtils.convertStringToDate(request.getStartDate()));
            promotionEntity.setEndDate(dateUtils.convertStringToDate(request.getEndDate()));
            promotionEntity.setCreationDate(new Date());
            promotionEntity.setModificationDate(new Date());
            iPromotionRepository.saveAndFlush(promotionEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public String update(PromotionUpdateRequest request) {
        String message = "Promotion successfully updated";
        try {
            CompanyEntity companyEntity = iCompanyRepository.findById(request.getCompanyId()).orElseThrow(() -> new Exception("Company not found"));
            PromotionTypeEntity promotionTypeEntity = iPromotionTypeRepository.findById(request.getPromotionTypeId()).orElseThrow(() -> new Exception("Promotion Type not found"));
            PromotionEntity promotionEntity = new PromotionEntity();
            promotionEntity.setTitle(request.getTitle());
            promotionEntity.setDuration(request.getDuration());
            promotionEntity.setDurationType(request.getDurationType());
            promotionEntity.setPromotionType(promotionTypeEntity);
            promotionEntity.setCompany(companyEntity);
            promotionEntity.setStatus(request.getStatus());
            promotionEntity.setPrice(request.getPrice());
            promotionEntity.setStartDate(dateUtils.convertStringToDate(request.getStartDate()));
            promotionEntity.setEndDate(dateUtils.convertStringToDate(request.getEndDate()));
            promotionEntity.setModificationDate(new Date());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public Slice<Promotion> getAllByFilter(PromotionGetAllByFilterRequest request) {
        Specification<PromotionEntity> specification = getSpecificationFilter(request);
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Slice<PromotionEntity> promotionEntitySlice = iPromotionRepository.findAll(specification, pageable);

        List<Promotion> promotionList = new ArrayList<>();
        promotionEntitySlice.forEach(promotionEntity -> {
            promotionList.add(ModelConvertionUtils.getPromotion(promotionEntity));
        });

        boolean hasNext = promotionList.size() > pageable.getPageSize();
        List<Promotion> sliceContent = hasNext ? promotionList.subList(0, pageable.getPageSize()) : promotionList;
        return new SliceImpl<>(sliceContent, pageable, hasNext);
    }

    @Override
    public Promotion getDetail(Integer id) {
        Promotion promotion = new Promotion();
        try {
            promotion = ModelConvertionUtils.getPromotion(iPromotionRepository.findById(id).orElseThrow(() -> new Exception("Promotion not found.")));
        } catch (Exception e) {
            log.error("Error promotion detail: {}", e.getMessage());
        }
        return promotion;
    }

    private Specification<PromotionEntity> getSpecificationFilter(PromotionGetAllByFilterRequest request) {
        Specification<PromotionEntity> specification = Specification.unrestricted();
        if (request.getTitle() != null) {
            specification = specification.and(PromotionSpecification.findByTitle(request.getTitle()));
        }
        if (request.getDurationPeriodMin() != null && request.getDurationPeriodMax() != null) {
            specification = specification.and(PromotionSpecification.findByDuration(request.getDurationPeriodMin(), request.getDurationPeriodMax()));
        }
        if (request.getDurationType() != null) {
            specification = specification.and(PromotionSpecification.findByDurationType(request.getDurationType().toString()));
        }
        if (request.getPromotionTypeId() != null) {
            specification = specification.and(PromotionSpecification.findByPromotionType(request.getPromotionTypeId()));
        }
        if (request.getCompanyId() != null) {
            specification = specification.and(PromotionSpecification.findByCompany(request.getCompanyId()));
        }
        if (request.getStatus() != null) {
            specification = specification.and(PromotionSpecification.findByStatus(request.getStatus()));
        }
        if (request.getStartDatePeriodStart() != null && request.getEndDatePeriodEnd() != null) {
            Date periodStart = dateUtils.convertStringToDate(request.getStartDatePeriodStart());
            Date periodEnd = dateUtils.convertStringToDate(request.getEndDatePeriodEnd());
            specification = specification.and(PromotionSpecification.findByStartAndEndDate(periodStart, periodEnd));
        }
        return specification;
    }
}
