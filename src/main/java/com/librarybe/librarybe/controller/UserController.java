package com.librarybe.librarybe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarybe.librarybe.dto.request.UserRequest;
import com.librarybe.librarybe.dto.response.ApiResponse;
import com.librarybe.librarybe.dto.response.UserResponse;
import com.librarybe.librarybe.entity.User;
import com.librarybe.librarybe.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        List<UserResponse> userResponses = userService.getAllUser();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", userResponses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", userResponse), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserRequest userRequest) {
        User users = userService.saveUser(userRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Success Created", users),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userService.updateUser(id, userRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success Updated", userResponse),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Succes Deleted", null),
                HttpStatus.NO_CONTENT);
    }

}
