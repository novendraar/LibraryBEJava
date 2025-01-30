package com.librarybe.librarybe.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BookRequest {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 100, message = "title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Synopsis cannot be blank")
    @Size(min = 2, message = "Synopsis must be at least 2 characters")
    private String synopsis;

    @Size(max = 10, message = "Stock maximal 10 characters")
    private Integer stock;

    private LocalDate publishedAt;

    @NotNull(message = "Choose one author")
    private Long authorId;

    @NotNull(message = "Choose one category")
    private Long categoryId;

    @NotNull(message = "Choose one publisher")
    private Long publisherId;
}
