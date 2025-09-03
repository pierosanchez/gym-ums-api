package com.pial.gym.gymapi.service.impl;

import com.pial.gym.gymapi.utils.JwtUtils;
import com.pial.gym.gymapi.dto.request.AuthenticationRequest;
import com.pial.gym.gymapi.dto.response.AuthenticationResponse;
import com.pial.gym.gymapi.service.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        try {
            Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword());
            Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);
            if (!authenticationResponse.isAuthenticated()) {
                log.error("Not authenticated");
                throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
            }
            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
            response.setToken(jwtUtils.generateToken(authenticationResponse));
        } catch (Exception e) {
            log.error("There was an error while authentication: {}", e.getMessage());
        }
        return response;
    }
}
