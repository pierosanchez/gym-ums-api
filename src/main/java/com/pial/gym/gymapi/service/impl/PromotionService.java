package com.pial.gym.gymapi.service.impl;

import com.pial.gym.gymapi.dto.request.PromotionCreateRequest;
import com.pial.gym.gymapi.entity.CompanyEntity;
import com.pial.gym.gymapi.entity.PromotionEntity;
import com.pial.gym.gymapi.entity.PromotionTypeEntity;
import com.pial.gym.gymapi.repository.ICompanyRepository;
import com.pial.gym.gymapi.repository.IPromotionRepository;
import com.pial.gym.gymapi.repository.IPromotionTypeRepository;
import com.pial.gym.gymapi.service.IPromotionService;
import com.pial.gym.gymapi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public String update() {
        return "";
    }
}
