package com.pial.gym.gymapi.dto.response;

import com.pial.gym.gymapi.dto.model.Role;
import lombok.Data;

@Data
public class RolePermissionGetAllResponse {
    private Role role;
    private Integer permissionsQuantity;
}
