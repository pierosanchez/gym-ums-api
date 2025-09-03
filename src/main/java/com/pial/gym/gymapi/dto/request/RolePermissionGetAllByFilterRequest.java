package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RolePermissionGetAllByFilterRequest {
    @NotNull(message = "Page Number Required")
    @NotBlank(message = "Page Number Required")
    private Integer pageNumber;
    @NotNull(message = "Page Size Required")
    @NotBlank(message = "Page Size Required")
    private Integer pageSize;
}
