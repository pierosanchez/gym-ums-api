package com.pial.gym.gymapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pial.gym.gymapi.enumerable.PromotionDurationTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate endDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate creationDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate modificationDate;
}
