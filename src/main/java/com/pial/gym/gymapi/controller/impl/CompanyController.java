package com.pial.gym.gymapi.controller.impl;

import com.pial.gym.gymapi.controller.ICompanyController;
import com.pial.gym.gymapi.dto.model.Company;
import com.pial.gym.gymapi.dto.request.CompanyCreateRequest;
import com.pial.gym.gymapi.dto.request.CompanyGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.CompanyUpdateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import com.pial.gym.gymapi.dto.response.ErrorResponse;
import com.pial.gym.gymapi.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyController implements ICompanyController {
    @Autowired
    private ICompanyService iCompanyService;

    @Override
    public ResponseEntity<BaseResponse<String>> create(CompanyCreateRequest request) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            String result = iCompanyService.create(request);
            baseResponse.setSuccess(true);
            baseResponse.setData(result);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(e.hashCode());
            errorResponse.setMessage(e.getMessage());

            baseResponse.setSuccess(false);
            baseResponse.setError(errorResponse);
        }
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public ResponseEntity<BaseResponse<String>> update(CompanyUpdateRequest request) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            String result = iCompanyService.update(request);
            baseResponse.setSuccess(true);
            baseResponse.setData(result);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(e.hashCode());
            errorResponse.setMessage(e.getMessage());

            baseResponse.setSuccess(false);
            baseResponse.setError(errorResponse);
        }
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public ResponseEntity<BaseResponse<Slice<Company>>> getAllByFilter(CompanyGetAllByFilterRequest request) {
        BaseResponse<Slice<Company>> baseResponse = new BaseResponse<>();
        try {
            Slice<Company> result = iCompanyService.getAllByFilter(request);
            baseResponse.setSuccess(true);
            baseResponse.setData(result);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(e.hashCode());
            errorResponse.setMessage(e.getMessage());

            baseResponse.setSuccess(false);
            baseResponse.setError(errorResponse);
        }
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public ResponseEntity<BaseResponse<Company>> getDetail(Integer id) {
        BaseResponse<Company> baseResponse = new BaseResponse<>();
        try {
            Company result = iCompanyService.getDetail(id);
            baseResponse.setSuccess(true);
            baseResponse.setData(result);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(e.hashCode());
            errorResponse.setMessage(e.getMessage());

            baseResponse.setSuccess(false);
            baseResponse.setError(errorResponse);
        }
        return ResponseEntity.ok(baseResponse);
    }
}
