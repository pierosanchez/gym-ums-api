package com.pial.gym.gymapi.controller.impl;

import com.pial.gym.gymapi.controller.IRolePermissionController;
import com.pial.gym.gymapi.dto.request.RolePermissionGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.RolePermissionCreateRequest;
import com.pial.gym.gymapi.dto.request.RolePermissionUpdateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import com.pial.gym.gymapi.dto.response.ErrorResponse;
import com.pial.gym.gymapi.dto.response.RolePermissionDetailResponse;
import com.pial.gym.gymapi.dto.response.RolePermissionGetAllResponse;
import com.pial.gym.gymapi.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionController implements IRolePermissionController {
    @Autowired
    private IRolePermissionService iRolePermissionService;

    @Override
    public ResponseEntity<BaseResponse<String>> create(RolePermissionCreateRequest request) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            String message = iRolePermissionService.create(request);
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
    public ResponseEntity<BaseResponse<RolePermissionDetailResponse>> getDetail(Integer roleId) {
        BaseResponse<RolePermissionDetailResponse> baseResponse = new BaseResponse<>();
        try {
            RolePermissionDetailResponse result = iRolePermissionService.getDetail(roleId);
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
    public ResponseEntity<BaseResponse<String>> update(RolePermissionUpdateRequest request) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            String message = iRolePermissionService.update(request);
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
    public ResponseEntity<BaseResponse<Slice<RolePermissionGetAllResponse>>> getAllByFilter(RolePermissionGetAllByFilterRequest request) {
        BaseResponse<Slice<RolePermissionGetAllResponse>> baseResponse = new BaseResponse<>();
        try {
            Slice<RolePermissionGetAllResponse> message = iRolePermissionService.getAllByFilter(request);
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
