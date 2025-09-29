package com.pial.gym.gymapi.dto.request;

import com.pial.gym.gymapi.enumerable.GenderEnum;
import com.pial.gym.gymapi.validator.EnumValue;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreateRequest {
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Min(value = 4, message = "Username not minor than 4 digits")
    private String username;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Min(value = 4, message = "Password not minor than 4 digits")
    private String password;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Last Name is required")
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotNull(message = "Phone Number is required")
    @NotBlank(message = "Phone Number is required")
    @Size(min = 7, max = 9, message = "Phone number must be between 7 and 9 digits")
    private String phone;
    @NotNull(message = "Gender is required")
    @EnumValue(enumClass = GenderEnum.class, message = "Invalid Gender")
    private String gender;
    @NotNull(message = "Role is required")
    private Integer roleId;
    @NotNull(message = "Company is required")
    private Integer companyId;
}
