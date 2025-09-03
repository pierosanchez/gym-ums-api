package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanyUpdateRequest {
    @NotNull(message = "Id is required")
    @NotBlank(message = "Id is required")
    private Integer id;
    @NotNull(message = "Description is required")
    private String description;
    @NotNull(message = "Description Long is required")
    private String descriptionLong;
    @NotNull(message = "Logo is required")
    private String logo;
    @NotNull(message = "Status is required")
    private Boolean status;
}
