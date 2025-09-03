package com.pial.gym.gymapi.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer code;
    private String message;
}
