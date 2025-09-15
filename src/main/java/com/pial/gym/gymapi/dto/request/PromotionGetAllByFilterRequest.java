package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PromotionGetAllByFilterRequest {
    @NotNull(message = "Page Number must not be empty")
    private Integer pageNumber;
    @NotNull(message = "Page Size must not be empty")
    private Integer pageSize;
    @NotNull(message = "Title must not be empty")
    private String title;
    @NotNull(message = "Duration Period Min must not be empty")
    private Integer durationPeriodMin;
    @NotNull(message = "Duration Period Max must not be empty")
    private Integer durationPeriodMax;
    @NotNull(message = "Duration Type must not be empty")
    private String durationType;
    @NotNull(message = "Promotion Type must not be empty")
    private Integer promotionTypeId;
    @NotNull(message = "Company must not be empty")
    private Integer companyId;
    @NotNull(message = "Status must not be empty")
    private Boolean status;
    @NotNull(message = "Start Date Period Start must not be empty")
    private String startDatePeriodStart;
    @NotNull(message = "Start Date Period End must not be empty")
    private String endDatePeriodEnd;
}
