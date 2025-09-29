package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.model.Membership;
import com.pial.gym.gymapi.dto.request.MembershipCreateRequest;
import com.pial.gym.gymapi.dto.request.MembershipGetAllByFilterRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public interface IMembershipService {
    String create(MembershipCreateRequest request);
    Slice<Membership> getAllByFilter(MembershipGetAllByFilterRequest request);
}
