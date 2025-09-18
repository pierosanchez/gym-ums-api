package com.pial.gym.gymapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RolePermission {
    private Integer id;
    private Role role;
    private Permission permission;
    private Boolean status;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate creationDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate modificationDate;
}
