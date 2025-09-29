package com.pial.gym.gymapi.controller;

import com.pial.gym.gymapi.dto.model.Promotion;
import com.pial.gym.gymapi.dto.request.PromotionCreateRequest;
import com.pial.gym.gymapi.dto.request.PromotionGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.PromotionUpdateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotion")
public interface IPromotionController {
    @PostMapping("/save")
    ResponseEntity<BaseResponse<String>> create(@Valid @RequestBody PromotionCreateRequest request);
    @PutMapping("/update")
    ResponseEntity<BaseResponse<String>> update(@Valid @RequestBody PromotionUpdateRequest request);
    @PostMapping("/filter")
    ResponseEntity<BaseResponse<Slice<Promotion>>> getAllByFilter(@Valid @RequestBody PromotionGetAllByFilterRequest request);
    @GetMapping("/detail/{id}")
    ResponseEntity<BaseResponse<Promotion>> getDetail(@PathVariable Integer id);
}
