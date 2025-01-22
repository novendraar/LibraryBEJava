package com.librarybe.librarybe.service.implementasi;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarybe.librarybe.controller.dto.request.UserRequest;
import com.librarybe.librarybe.controller.dto.response.UserResponse;
import com.librarybe.librarybe.entity.User;
import com.librarybe.librarybe.exception.ResourceNotFoundException;
import com.librarybe.librarybe.repository.UserRepository;
import com.librarybe.librarybe.service.UserService;

@Service
public class UserImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserImplementation() {
        this.modelMapper = new ModelMapper();
    }
    // private ModelMapper modelMapper() {
    // return new ModelMapper();
    // }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.getAllUser();
        if (users == null || users.isEmpty()) {
            throw new ResourceNotFoundException("Data Not Found");
        }
        List<UserResponse> userResponses = users.stream().map(src -> modelMapper.map(src, UserResponse.class))
                .collect(Collectors.toList());
        return userResponses;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User users = userRepository.getUserById(id);
        if (users == null) {
            throw new ResourceNotFoundException("User with ID: " + id + " Not Found");
        }
        UserResponse userResponses = modelMapper.map(users, UserResponse.class);
        return userResponses;
    }

    @Override
    public User saveUser(UserRequest userRequest) {
        if (userRequest.getName().isEmpty() || userRequest.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        boolean phoneNumberExists = userRepository.existsByPhoneNumber(userRequest.getPhoneNumber());
        if (phoneNumberExists) {
            throw new IllegalArgumentException("Phone number already exists " + userRequest.getPhoneNumber());
        }
        User users = modelMapper.map(userRequest, User.class);
        return userRepository.save(users);
    }

    @Override
    public void deleteUserById(Long id) {
        User users = userRepository.getUserById(id);
        if (users == null) {
            throw new ResourceNotFoundException("User with ID: " + id + " Not Found");
        }
        users.setIsDeleted(true);
        userRepository.save(users);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User users = userRepository.getUserById(id);
        if (users == null) {
            throw new ResourceNotFoundException("User with ID: " + id + " Not Found");
        }
        users.setName(userRequest.getName());
        users.setPhoneNumber(userRequest.getPhoneNumber());
        users.setAddress(userRequest.getAddress());
        userRepository.save(users);
        UserResponse userResponses = modelMapper.map(users, UserResponse.class);
        return userResponses;
    }

}
