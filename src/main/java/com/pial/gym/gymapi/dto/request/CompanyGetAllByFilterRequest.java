package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanyGetAllByFilterRequest {
    @NotNull(message = "Page Number must not be empty")
    private Integer pageNumber;
    @NotNull(message = "Page Size must not be empty")
    private Integer pageSize;
    private String description;
    private String descriptionLong;
    private Boolean status;
}
