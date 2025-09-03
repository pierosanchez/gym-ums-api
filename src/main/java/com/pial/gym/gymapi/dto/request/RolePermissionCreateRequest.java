package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RolePermissionCreateRequest {
    @NotNull(message = "Role is required")
    @NotBlank(message = "Role is required")
    private Integer roleId;
    @NotNull(message = "Permission is required")
    @NotBlank(message = "Permission is required")
    private List<Integer> listPermissionId;
}
