package com.pial.gym.gymapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MembershipPeriod {
    private Integer id;
    private Membership membership;
    private Promotion promotion;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate endDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate creationDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate modificationDate;
}
