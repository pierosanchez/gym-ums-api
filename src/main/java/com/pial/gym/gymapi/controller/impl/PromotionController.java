package com.pial.gym.gymapi.controller.impl;

import com.pial.gym.gymapi.controller.IPromotionController;
import com.pial.gym.gymapi.dto.model.Promotion;
import com.pial.gym.gymapi.dto.request.PromotionCreateRequest;
import com.pial.gym.gymapi.dto.request.PromotionGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.PromotionUpdateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import com.pial.gym.gymapi.dto.response.ErrorResponse;
import com.pial.gym.gymapi.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PromotionController implements IPromotionController {
    @Autowired
    private IPromotionService iPromotionService;

    @Override
    public ResponseEntity<BaseResponse<String>> save(PromotionCreateRequest request) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            String message = iPromotionService.create(request);
            baseResponse.setSuccess(true);
            baseResponse.setData(message);
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
    public ResponseEntity<BaseResponse<String>> update(PromotionUpdateRequest request) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            String message = iPromotionService.update(request);
            baseResponse.setSuccess(true);
            baseResponse.setData(message);
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
    public ResponseEntity<BaseResponse<Slice<Promotion>>> getAllByFilter(PromotionGetAllByFilterRequest request) {
        BaseResponse<Slice<Promotion>> baseResponse = new BaseResponse<>();
        try {
            Slice<Promotion> message = iPromotionService.getAllByFilter(request);
            baseResponse.setSuccess(true);
            baseResponse.setData(message);
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
    public ResponseEntity<BaseResponse<Promotion>> getDetail(Integer id) {
        BaseResponse<Promotion> baseResponse = new BaseResponse<>();
        try {
            Promotion message = iPromotionService.getDetail(id);
            baseResponse.setSuccess(true);
            baseResponse.setData(message);
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
