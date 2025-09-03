package com.pial.gym.gymapi.dto.request;

import lombok.Data;

@Data
public class CompanyGetAllByFilterRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String description;
    private String descriptionLong;
    private Boolean status;
}
