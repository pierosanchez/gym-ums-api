package com.pial.gym.gymapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @NotNull(message = "Username required")
    @NotBlank(message = "Username required")
    private String username;
    @NotNull(message = "Password required")
    @NotBlank(message = "Password required")
    private String password;
}
