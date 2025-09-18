package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.request.MembershipCreateRequest;
import org.springframework.stereotype.Component;

@Component
public interface IMembershipService {
    String create(MembershipCreateRequest request);
}
