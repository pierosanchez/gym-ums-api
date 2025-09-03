package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsersGetAllByFilterRequest {
    @NotNull(message = "Page Number Required")
    @NotBlank(message = "Page Number Required")
    private Integer pageNumber;
    @NotNull(message = "Page Size Required")
    @NotBlank(message = "Page Size Required")
    private Integer pageSize;
    @NotNull(message = "Username Required")
    private String username;
    @NotNull(message = "Name Required")
    private String name;
    @NotNull(message = "Last Name Required")
    private String lastName;
    @NotNull(message = "Email Required")
    private String email;
    @NotNull(message = "Gender Required")
    private String gender;
    @NotNull(message = "Role Required")
    private Integer roleId;
    @NotNull(message = "Company Required")
    private Integer companyId;
    @NotNull(message = "Status Required")
    private Boolean status;
    @NotNull(message = "Check Creation Date Required")
    private Boolean isFilterCreationDate;
    @NotNull(message = "Check Modification Date Required")
    private Boolean isFilterModificationDate;
    @NotNull(message = "From Required")
    private String fromDate;
    @NotNull(message = "To Date Required")
    private String toDate;
}
