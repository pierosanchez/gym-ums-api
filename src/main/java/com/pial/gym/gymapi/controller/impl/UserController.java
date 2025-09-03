package com.pial.gym.gymapi.controller.impl;

import com.pial.gym.gymapi.controller.IUserController;
import com.pial.gym.gymapi.dto.model.User;
import com.pial.gym.gymapi.dto.request.UsersGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.UserCreateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import com.pial.gym.gymapi.dto.response.ErrorResponse;
import com.pial.gym.gymapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserController implements IUserController {
    @Autowired
    private IUserService iUserService;

    @Override
    public ResponseEntity<BaseResponse<String>> create(UserCreateRequest request) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            String message = iUserService.create(request);
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
    public ResponseEntity<BaseResponse<Slice<User>>> getUserByFilter(UsersGetAllByFilterRequest request) {
        BaseResponse<Slice<User>> baseResponse = new BaseResponse<>();
        try {
            Slice<User> list = iUserService.getAllByFilter(request);
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

    @Override
    public ResponseEntity<BaseResponse<User>> getUserDetail(Integer id) {
        BaseResponse<User> baseResponse = new BaseResponse<>();
        try {
            User obj = iUserService.getDetail(id);
            baseResponse.setSuccess(true);
            baseResponse.setData(obj);
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
