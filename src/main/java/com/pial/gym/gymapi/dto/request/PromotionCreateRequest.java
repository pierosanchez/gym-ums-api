package com.pial.gym.gymapi.dto.request;

import com.pial.gym.gymapi.enumerable.PromotionDurationTypeEnum;
import com.pial.gym.gymapi.validator.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Data
public class PromotionCreateRequest {
    @NotBlank(message = "Title is required")
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Duration is required")
    private Integer duration;
    @NotNull(message = "Duration Type is required")
    @EnumValue(enumClass = PromotionDurationTypeEnum.class, message = "Invalid Duration Type")
    private String durationType;
    @NotNull(message = "Promotion Type is required")
    private Integer promotionTypeId;
    @NotNull(message = "Company is required")
    private Integer companyId;
    @NotNull(message = "Status is required")
    private Boolean status;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @NotBlank(message = "Start Date is required")
    @NotNull(message = "Start Date is required")
    private String startDate;
    @NotBlank(message = "End Date is required")
    @NotNull(message = "End Date is required")
    private String endDate;
}
