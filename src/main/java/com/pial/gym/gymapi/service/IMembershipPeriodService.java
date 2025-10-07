package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.model.MembershipPeriod;
import com.pial.gym.gymapi.dto.request.MembershipPeriodCreateRequest;
import com.pial.gym.gymapi.dto.request.MembershipPeriodGetAllByFilter;
import com.pial.gym.gymapi.dto.request.MembershipPeriodUpdateRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public interface IMembershipPeriodService {
    String create(MembershipPeriodCreateRequest request);
    String update(MembershipPeriodUpdateRequest request);
    Slice<MembershipPeriod> getAllByFilter(MembershipPeriodGetAllByFilter request);
}
