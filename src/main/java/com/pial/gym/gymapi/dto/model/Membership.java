package com.pial.gym.gymapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pial.gym.gymapi.enumerable.MembershipStatusEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Membership {
    private Integer id;
    private User userRegister;
    private User userClient;
    private Company company;
    private MembershipStatusEnum status;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate creationDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate modificationDate;
}
