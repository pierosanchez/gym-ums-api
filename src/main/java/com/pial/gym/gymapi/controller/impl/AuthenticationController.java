package com.pial.gym.gymapi.controller.impl;

import com.pial.gym.gymapi.controller.IAuthenticationController;
import com.pial.gym.gymapi.dto.request.AuthenticationRequest;
import com.pial.gym.gymapi.dto.response.AuthenticationResponse;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import com.pial.gym.gymapi.dto.response.ErrorResponse;
import com.pial.gym.gymapi.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationController implements IAuthenticationController {
    @Autowired
    private IAuthenticationService iAuthenticationService;

    @Override
    public ResponseEntity<BaseResponse<AuthenticationResponse>> authenticate(AuthenticationRequest request) {
        BaseResponse<AuthenticationResponse> baseResponse = new BaseResponse<>();
        try {
            AuthenticationResponse result = iAuthenticationService.authentication(request);
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
