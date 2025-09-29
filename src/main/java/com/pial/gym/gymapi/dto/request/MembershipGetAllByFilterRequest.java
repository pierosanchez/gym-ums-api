package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MembershipGetAllByFilterRequest {
    @NotNull(message = "Page Number must not be empty")
    private Integer pageNumber;
    @NotNull(message = "Page Size must not be empty")
    private Integer pageSize;
    @NotNull(message = "User Name Register must not be empty")
    private String userNameRegister;
    @NotNull(message = "User Name Client must not be empty")
    private String userNameClient;
    @NotNull(message = "Company must not be empty")
    private Integer companyId;
    @NotNull(message = "Status must not be empty")
    private String status;
}
