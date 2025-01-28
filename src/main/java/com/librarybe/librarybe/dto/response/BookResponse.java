package com.librarybe.librarybe.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;

    private String title;
    private String synopsis;
    private Integer stock;
    private LocalDate publishedAt;

    private String authorsName;
    private String categoryName;
    private String publisherName;

    private Long authorId;
    private Long categoryId;
    private Long publisherId;
}
