package com.pial.gym.gymapi.controller;

import com.pial.gym.gymapi.dto.model.User;
import com.pial.gym.gymapi.dto.request.UsersGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.UserCreateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public interface IUserController {
    @PostMapping("/save")
    ResponseEntity<BaseResponse<String>> create(@Valid @RequestBody UserCreateRequest request);
    @PostMapping("/filter")
    ResponseEntity<BaseResponse<Slice<User>>> getAllByFilter(@Valid @RequestBody UsersGetAllByFilterRequest request);
    @GetMapping("/detail/{id}")
    ResponseEntity<BaseResponse<User>> getDetail(@PathVariable Integer id);
}
