package com.pial.gym.gymapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;
    private String description;
    private String code;
    private Boolean visible;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date creationDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date modificationDate;
}
