package com.pial.gym.gymapi.controller;

import com.pial.gym.gymapi.dto.request.RolePermissionGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.RolePermissionCreateRequest;
import com.pial.gym.gymapi.dto.request.RolePermissionUpdateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import com.pial.gym.gymapi.dto.response.RolePermissionDetailResponse;
import com.pial.gym.gymapi.dto.response.RolePermissionGetAllResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role-permission")
public interface IRolePermissionController {
    @PostMapping("/save")
    ResponseEntity<BaseResponse<String>> create(@Valid @RequestBody RolePermissionCreateRequest request);
    @GetMapping("/detail/{roleId}")
    ResponseEntity<BaseResponse<RolePermissionDetailResponse>> getDetail(@Valid @PathVariable Integer roleId);
    @PutMapping("/update")
    ResponseEntity<BaseResponse<String>> update(@Valid @RequestBody RolePermissionUpdateRequest request);
    @PostMapping("/filter")
    ResponseEntity<BaseResponse<Slice<RolePermissionGetAllResponse>>> getAllByFilter(@Valid @RequestBody RolePermissionGetAllByFilterRequest request);
}
