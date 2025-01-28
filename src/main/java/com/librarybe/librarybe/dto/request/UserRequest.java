package com.librarybe.librarybe.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 15, message = "Phone number max 15 characters")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 2, message = "Address must be at least 2 characters")
    private String address;
}
