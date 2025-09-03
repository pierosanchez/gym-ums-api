package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.model.Company;
import com.pial.gym.gymapi.dto.request.CompanyCreateRequest;
import com.pial.gym.gymapi.dto.request.CompanyGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.CompanyUpdateRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public interface ICompanyService {
    String create(CompanyCreateRequest request);
    String update(CompanyUpdateRequest request);
    Slice<Company> getAllByFilter(CompanyGetAllByFilterRequest request);
    Company getDetail(Integer id);
}
