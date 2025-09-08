package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.request.PromotionCreateRequest;
import com.pial.gym.gymapi.dto.request.PromotionUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public interface IPromotionService {
    String create(PromotionCreateRequest request);
    String update(PromotionUpdateRequest request);

}
