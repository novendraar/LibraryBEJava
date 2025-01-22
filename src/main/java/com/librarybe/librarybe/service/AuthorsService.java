package com.librarybe.librarybe.service;

import java.util.List;

import com.librarybe.librarybe.controller.dto.request.AuthorsRequest;
import com.librarybe.librarybe.controller.dto.response.AuthorsResponse;
import com.librarybe.librarybe.entity.Authors;

public interface AuthorsService {
    List<AuthorsResponse> getAllAuthors();

    AuthorsResponse getAuthorById(Long id);

    Authors saveAuthors(AuthorsRequest authorsRequest);

    AuthorsResponse updateAuthors(Long id, AuthorsRequest authorsRequest);

    void deleteAuthorsById(Long id);
}
