package com.librarybe.librarybe.dto.response;

import lombok.Data;

@Data
public class AuthorsResponse {
    private Long id;
    private String name;
    private Integer publishedBooks;
}
