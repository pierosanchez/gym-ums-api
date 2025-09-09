package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.model.Promotion;
import com.pial.gym.gymapi.dto.request.PromotionCreateRequest;
import com.pial.gym.gymapi.dto.request.PromotionGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.PromotionUpdateRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public interface IPromotionService {
    String create(PromotionCreateRequest request);
    String update(PromotionUpdateRequest request);
    Slice<Promotion> getAllByFilter(PromotionGetAllByFilterRequest request);
    Promotion getDetail(Integer id);
}
