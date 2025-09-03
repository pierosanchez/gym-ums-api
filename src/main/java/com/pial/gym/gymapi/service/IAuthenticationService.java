package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.request.AuthenticationRequest;
import com.pial.gym.gymapi.dto.response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public interface IAuthenticationService {
    AuthenticationResponse authentication(AuthenticationRequest request);
}
