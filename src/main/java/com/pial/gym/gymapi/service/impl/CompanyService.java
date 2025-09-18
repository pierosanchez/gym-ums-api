package com.pial.gym.gymapi.service.impl;

import com.pial.gym.gymapi.dto.model.Company;
import com.pial.gym.gymapi.dto.request.CompanyCreateRequest;
import com.pial.gym.gymapi.dto.request.CompanyGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.CompanyUpdateRequest;
import com.pial.gym.gymapi.entity.CompanyEntity;
import com.pial.gym.gymapi.repository.ICompanyRepository;
import com.pial.gym.gymapi.service.ICompanyService;
import com.pial.gym.gymapi.specification.CompanySpecification;
import com.pial.gym.gymapi.utils.ModelConvertionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private ICompanyRepository iCompanyRepository;

    @Override
    public String create(CompanyCreateRequest request) {
        String message = "Company successfully created";
        try {
            CompanyEntity companyEntity = new CompanyEntity();
            companyEntity.setDescription(request.getDescription());
            companyEntity.setDescriptionLong(request.getDescriptionLong());
            companyEntity.setLogo(request.getLogo());
            companyEntity.setStatus(true);
            companyEntity.setCreationDate(LocalDate.now());
            companyEntity.setModificationDate(LocalDate.now());
            iCompanyRepository.saveAndFlush(companyEntity);
        } catch (Exception e) {
            log.error("There was an error while creating the company: {}", e.getMessage());
        }
        return message;
    }

    @Override
    public String update(CompanyUpdateRequest request) {
        String message = "Company successfully updated";
        try {
            CompanyEntity companyEntity = iCompanyRepository.findById(request.getId()).orElseThrow(() -> new Exception("Company not found"));
            companyEntity.setDescription(request.getDescription());
            companyEntity.setDescriptionLong(request.getDescriptionLong());
            companyEntity.setLogo(request.getLogo());
            companyEntity.setStatus(request.getStatus());
            companyEntity.setModificationDate(LocalDate.now());
            iCompanyRepository.saveAndFlush(companyEntity);
        } catch (Exception e) {
            log.error("There was an error while updating the company: {}", e.getMessage());
        }
        return message;
    }

    @Override
    public Slice<Company> getAllByFilter(CompanyGetAllByFilterRequest request) {
        Specification<CompanyEntity> specification = getSpecificationFilter(request);

        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Slice<CompanyEntity> companyEntitySlice = iCompanyRepository.findAll(specification, pageable);
        List<Company> companyList = new ArrayList<>();
        companyEntitySlice.forEach(companyEntity -> {
            companyList.add(ModelConvertionUtils.getCompany(companyEntity));
        });

        boolean hasNext = companyList.size() > pageable.getPageSize();
        List<Company> sliceContent = hasNext ? companyList.subList(0, pageable.getPageSize()) : companyList;

        return new SliceImpl<>(sliceContent, pageable, hasNext);
    }

    @Override
    public Company getDetail(Integer id) {
        Company company = new Company();
        try {
            company = ModelConvertionUtils.getCompany(iCompanyRepository.findById(id).orElseThrow(() -> new Exception("Company not found")));
        } catch (Exception e) {
            log.error("There was an error while obtaining the company: {}", e.getMessage());
        }
        return company;
    }

    private Specification<CompanyEntity> getSpecificationFilter(CompanyGetAllByFilterRequest request) {
        Specification<CompanyEntity> specification = Specification.unrestricted();
        if (request.getDescription() != null) {
            specification = specification.and(CompanySpecification.findByDescription(request.getDescription()));
        }
        if (request.getDescriptionLong() != null) {
            specification = specification.and(CompanySpecification.findByDescriptionLong(request.getDescriptionLong()));
        }
        if (request.getStatus() != null) {
            specification = specification.and(CompanySpecification.findByStatus(request.getStatus()));
        }
        return specification;
    }
}
