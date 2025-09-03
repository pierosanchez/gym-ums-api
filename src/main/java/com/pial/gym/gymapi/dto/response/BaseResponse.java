package com.pial.gym.gymapi.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class BaseResponse<T> {
    private boolean success;
    private T data;
    private ErrorResponse error;
}
