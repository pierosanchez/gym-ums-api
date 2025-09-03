package com.pial.gym.gymapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pial.gym.gymapi.dto.model.Permission;
import com.pial.gym.gymapi.dto.model.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RolePermissionDetailResponse {
    private Role role;
    private List<Permission> permissionList;
}
