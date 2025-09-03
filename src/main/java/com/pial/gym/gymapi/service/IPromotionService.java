package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.request.PromotionCreateRequest;
import org.springframework.stereotype.Component;

@Component
public interface IPromotionService {
    String create(PromotionCreateRequest request);
    String update();
}
