package com.pial.gym.gymapi.controller;

import com.pial.gym.gymapi.dto.model.Company;
import com.pial.gym.gymapi.dto.request.CompanyCreateRequest;
import com.pial.gym.gymapi.dto.request.CompanyGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.CompanyUpdateRequest;
import com.pial.gym.gymapi.dto.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public interface ICompanyController {
    @PostMapping("/save")
    ResponseEntity<BaseResponse<String>> create(@Valid @RequestBody CompanyCreateRequest request);
    @PutMapping("/update")
    ResponseEntity<BaseResponse<String>> update(@Valid @RequestBody CompanyUpdateRequest request);
    @PostMapping("/filter")
    ResponseEntity<BaseResponse<Slice<Company>>> getAllByFilter(@RequestBody CompanyGetAllByFilterRequest request);
    @GetMapping("/detail/{id}")
    ResponseEntity<BaseResponse<Company>> getDetail(@PathVariable Integer id);
}
