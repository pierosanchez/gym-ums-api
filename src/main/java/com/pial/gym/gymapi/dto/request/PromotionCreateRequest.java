package com.pial.gym.gymapi.dto.request;

import com.pial.gym.gymapi.enumerable.PromotionDurationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PromotionCreateRequest {
    @NotBlank(message = "Title is required")
    @NotNull(message = "Title is required")
    private String title;
    @NotBlank(message = "Duration is required")
    @NotNull(message = "Duration is required")
    private Integer duration;
    @NotBlank(message = "Duration Type is required")
    @NotNull(message = "Duration Type is required")
    private PromotionDurationTypeEnum durationType;
    @NotNull(message = "Promotion Type is required")
    private Integer promotionTypeId;
    @NotNull(message = "Company is required")
    private Integer companyId;
    @NotNull(message = "Status is required")
    private Boolean status;
    @NotBlank(message = "Start Date is required")
    @NotNull(message = "Start Date is required")
    private String startDate;
    @NotBlank(message = "End Date is required")
    @NotNull(message = "End Date is required")
    private String endDate;
}
