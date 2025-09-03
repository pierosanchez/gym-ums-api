package com.pial.gym.gymapi.service;

import com.pial.gym.gymapi.dto.model.User;
import com.pial.gym.gymapi.dto.request.UsersGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.UserCreateRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public interface IUserService {
    String create(UserCreateRequest request);
    Slice<User> getAllByFilter(UsersGetAllByFilterRequest request);
    User getDetail(Integer id);
}
