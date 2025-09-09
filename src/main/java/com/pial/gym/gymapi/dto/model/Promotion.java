package com.pial.gym.gymapi.dto.model;

import com.pial.gym.gymapi.enumerable.PromotionDurationTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Promotion {
    private Integer id;
    private String title;
    private Integer duration;
    private PromotionDurationTypeEnum durationType;
    private PromotionType promotionType;
    private Company company;
    private Boolean status;
    private BigDecimal price;
    private Date startDate;
    private Date endDate;
    private Date creationDate;
    private Date modificationDate;
}
