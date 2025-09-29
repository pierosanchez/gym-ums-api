package com.pial.gym.gymapi.controller;

import com.pial.gym.gymapi.dto.model.Membership;
import com.pial.gym.gymapi.dto.request.MembershipCreateRequest;
import com.pial.gym.gymapi.dto.request.MembershipGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
public interface IMembershipController {
    @PostMapping("/create")
    ResponseEntity<BaseResponse<String>> create(@Valid @RequestBody MembershipCreateRequest request);
    @PostMapping("/filter")
    ResponseEntity<BaseResponse<Slice<Membership>>> getAllByFilter(@Valid @RequestBody MembershipGetAllByFilterRequest request);
}
