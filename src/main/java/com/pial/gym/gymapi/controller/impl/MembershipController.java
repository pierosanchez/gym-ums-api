package com.pial.gym.gymapi.controller.impl;

import com.pial.gym.gymapi.controller.IMembershipController;
import com.pial.gym.gymapi.dto.model.Membership;
import com.pial.gym.gymapi.dto.request.MembershipCreateRequest;
import com.pial.gym.gymapi.dto.request.MembershipGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import com.pial.gym.gymapi.dto.response.ErrorResponse;
import com.pial.gym.gymapi.service.IMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MembershipController implements IMembershipController {
    @Autowired
    private IMembershipService iMembershipService;

    @Override
    public ResponseEntity<BaseResponse<String>> create(MembershipCreateRequest request) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            String message = iMembershipService.create(request);
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
    public ResponseEntity<BaseResponse<Slice<Membership>>> getAllByFilter(MembershipGetAllByFilterRequest request) {
        BaseResponse<Slice<Membership>> baseResponse = new BaseResponse<>();
        try {
            Slice<Membership> list = iMembershipService.getAllByFilter(request);
            baseResponse.setSuccess(true);
            baseResponse.setData(list);
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
