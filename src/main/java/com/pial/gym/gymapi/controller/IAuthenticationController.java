package com.pial.gym.gymapi.controller;

import com.pial.gym.gymapi.dto.request.AuthenticationRequest;
import com.pial.gym.gymapi.dto.response.AuthenticationResponse;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public interface IAuthenticationController {
    @PostMapping("/authenticate")
    ResponseEntity<BaseResponse<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest request);
}
