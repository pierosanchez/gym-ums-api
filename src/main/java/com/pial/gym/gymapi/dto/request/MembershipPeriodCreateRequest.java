package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MembershipPeriodCreateRequest {
    @NotNull(message = "Membership is required")
    private Integer membershipId;
    @NotNull(message = "Promotion is required")
    private Integer promotionId;
    @NotNull(message = "Start Period Date is required")
    @NotBlank(message = "Start Period Date is required")
    private String startPeriodDate;
    @NotNull(message = "User Relation Promotion flag is required")
    private boolean isUserRelationPromotion;
    private List<Integer> userClientRelationIdList;
}
