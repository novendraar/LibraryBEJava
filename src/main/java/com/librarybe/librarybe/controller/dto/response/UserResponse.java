package com.librarybe.librarybe.controller.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
}
