package com.pial.gym.gymapi.controller;

import com.pial.gym.gymapi.dto.model.User;
import com.pial.gym.gymapi.dto.request.UsersGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.UserCreateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public interface IUserController {
    @PostMapping("/save")
    ResponseEntity<BaseResponse<String>> create(@RequestBody UserCreateRequest request);
    @PostMapping("/get")
    ResponseEntity<BaseResponse<Slice<User>>> getUserByFilter(@RequestBody UsersGetAllByFilterRequest request);
    @GetMapping("/detail/{id}")
    ResponseEntity<BaseResponse<User>> getUserDetail(@PathVariable Integer id);
}
