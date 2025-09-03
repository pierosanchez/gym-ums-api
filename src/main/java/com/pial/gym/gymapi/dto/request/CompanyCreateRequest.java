package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanyCreateRequest {
    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Description Long is required")
    @NotBlank(message = "Description Long is required")
    private String descriptionLong;
    @NotNull(message = "Logo is required")
    @NotBlank(message = "Logo is required")
    private String logo;
}
