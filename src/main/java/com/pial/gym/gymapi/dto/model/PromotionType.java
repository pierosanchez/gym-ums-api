package com.pial.gym.gymapi.dto.model;

import lombok.Data;

@Data
public class PromotionType {
    private Integer id;
    private String description;
    private Boolean isUserRelation;
    private Boolean isRegular;
}
