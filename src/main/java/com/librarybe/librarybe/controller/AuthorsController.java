package com.librarybe.librarybe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarybe.librarybe.controller.dto.request.AuthorsRequest;
import com.librarybe.librarybe.controller.dto.response.ApiResponse;
import com.librarybe.librarybe.controller.dto.response.AuthorsResponse;
import com.librarybe.librarybe.entity.Authors;
import com.librarybe.librarybe.service.AuthorsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/authors")
@Validated
public class AuthorsController {

    @Autowired
    AuthorsService authorsService;

    @GetMapping("")
    public ResponseEntity<?> getAllAuthors() {
        List<AuthorsResponse> authors = authorsService.getAllAuthors();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", authors), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorsById(@PathVariable Long id) {
        AuthorsResponse authorsResponse = authorsService.getAuthorById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", authorsResponse),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveAuthors(@RequestBody @Valid AuthorsRequest authorsRequest) {
        Authors authors = authorsService.saveAuthors(authorsRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Success Created", authors),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthors(@PathVariable Long id, @RequestBody @Valid AuthorsRequest authorsRequest) {
        AuthorsResponse authorsResponse = authorsService.updateAuthors(id, authorsRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success Updated", authorsResponse),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthors(@PathVariable Long id) {
        authorsService.deleteAuthorsById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Success Deleted", null),
                HttpStatus.NO_CONTENT);
    }

}
