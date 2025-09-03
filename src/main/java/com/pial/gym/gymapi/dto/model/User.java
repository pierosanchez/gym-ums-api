package com.pial.gym.gymapi.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String lasName;
    private String email;
    private String phone;
    private String gender;
    private Role role;
    private Company company;
    private Boolean status;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date creationDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date modificationDate;
}
