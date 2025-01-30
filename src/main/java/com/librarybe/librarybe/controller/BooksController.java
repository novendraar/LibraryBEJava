package com.librarybe.librarybe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarybe.librarybe.dto.request.BookRequest;
import com.librarybe.librarybe.dto.response.ApiResponse;
import com.librarybe.librarybe.dto.response.BookResponse;
import com.librarybe.librarybe.entity.Books;
import com.librarybe.librarybe.service.AuthorsService;
import com.librarybe.librarybe.service.BookService;
import com.librarybe.librarybe.service.CategoryService;
import com.librarybe.librarybe.service.PublisherService;
import com.librarybe.librarybe.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/books")
@Validated
public class BooksController {
    @Autowired
    AuthorsService authorsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PublisherService publisherService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @GetMapping("")
    public ResponseEntity<?> getAllBooks() {
        List<BookResponse> booksResponse = bookService.getAllBooks();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", booksResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooksById(@PathVariable Long id) {
        BookResponse bookResponse = bookService.getBookById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", bookResponse), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveBooks(@RequestBody @Valid BookRequest bookRequest) {
        Books book = bookService.saveBooks(bookRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Success Created", book),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooks(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest) {
        BookResponse bookResponse = bookService.updateBook(id, bookRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success Updated", bookResponse),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooks(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Success Deleted", null),
                HttpStatus.NO_CONTENT);
    }
}
