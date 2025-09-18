package com.pial.gym.gymapi.dto.request;

import com.pial.gym.gymapi.enumerable.MembershipStatusEnum;
import com.pial.gym.gymapi.validator.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MembershipCreateRequest {
    @NotNull(message = "Client is required")
    private Integer userClientId;
    @NotNull(message = "Company is required")
    private Integer companyId;
    @NotNull(message = "Promotion is required")
    private Integer promotionId;
    @NotNull(message = "User Relation Promotion flag is required")
    private boolean isUserRelationPromotion;
    private List<Integer> userClientRelationIdList;
    @NotNull(message = "Status is required")
    @EnumValue(enumClass = MembershipStatusEnum.class, message = "Invalid Status")
    private String status;
    @NotNull(message = "Start Period Date is required")
    @NotBlank(message = "Start Period Date is required")
    private String startPeriodDate;
}
