package com.librarybe.librarybe.service;

import java.util.List;

import com.librarybe.librarybe.dto.request.BookRequest;
import com.librarybe.librarybe.dto.response.BookResponse;
import com.librarybe.librarybe.entity.Books;

public interface BookService {
    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    Books saveBooks(BookRequest bookRequest);

    BookResponse updateBook(Long id, BookRequest bookRequest);

    void deleteBookById(Long id);
}
