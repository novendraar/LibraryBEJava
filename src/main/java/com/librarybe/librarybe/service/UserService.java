package com.librarybe.librarybe.service;

import java.util.List;

import com.librarybe.librarybe.controller.dto.request.UserRequest;
import com.librarybe.librarybe.controller.dto.response.UserResponse;
import com.librarybe.librarybe.entity.User;

public interface UserService {
    List<UserResponse> getAllUser();

    UserResponse getUserById(Long id);

    User saveUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    void deleteUserById(Long id);

}
