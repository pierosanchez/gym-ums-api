package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.request.RolePermissionGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.RolePermissionCreateRequest;
import com.pial.gym.gymapi.dto.request.RolePermissionUpdateRequest;
import com.pial.gym.gymapi.dto.response.RolePermissionDetailResponse;
import com.pial.gym.gymapi.dto.response.RolePermissionGetAllResponse;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public interface IRolePermissionService {
    String create(RolePermissionCreateRequest request);
    RolePermissionDetailResponse getDetail(Integer roleId);
    String update(RolePermissionUpdateRequest request);
    Slice<RolePermissionGetAllResponse> getAllByFilter(RolePermissionGetAllByFilterRequest request);
}
