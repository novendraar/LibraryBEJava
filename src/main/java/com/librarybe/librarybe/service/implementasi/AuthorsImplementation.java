package com.librarybe.librarybe.service.implementasi;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarybe.librarybe.controller.dto.request.AuthorsRequest;
import com.librarybe.librarybe.controller.dto.response.AuthorsResponse;
import com.librarybe.librarybe.entity.Authors;
import com.librarybe.librarybe.exception.ResourceNotFoundException;
import com.librarybe.librarybe.repository.AuthorsRepository;
import com.librarybe.librarybe.service.AuthorsService;

@Service
public class AuthorsImplementation implements AuthorsService {

    @Autowired
    AuthorsRepository authorsRepository;

    private final ModelMapper modelMapper;

    public AuthorsImplementation() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<AuthorsResponse> getAllAuthors() {
        List<Authors> authors = authorsRepository.getAllAuthors();
        if (authors == null || authors.isEmpty()) {
            throw new ResourceNotFoundException("Data Not Found");
        }
        return authors.stream()
                .map(src -> modelMapper.map(src, AuthorsResponse.class)).collect(Collectors.toList());
    }

    @Override
    public AuthorsResponse getAuthorById(Long id) {
        Authors authors = authorsRepository.getAuthorsById(id);
        if (authors == null) {
            throw new ResourceNotFoundException("User with ID: " + id + " Not Found");
        }
        return modelMapper.map(authors, AuthorsResponse.class);
    }

    @Override
    public Authors saveAuthors(AuthorsRequest authorsRequest) {
        if (authorsRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        return authorsRepository.save(modelMapper.map(authorsRequest, Authors.class));
    }

    @Override
    public AuthorsResponse updateAuthors(Long id, AuthorsRequest authorsRequest) {
        Authors authors = authorsRepository.getAuthorsById(id);
        if (authors == null) {
            throw new ResourceNotFoundException("User with ID: " + id + " Not Found");
        }
        authors.setName(authorsRequest.getName());
        authors.setPublishedBooks(authorsRequest.getPublishedBooks());
        authorsRepository.save(authors);
        return modelMapper.map(authors, AuthorsResponse.class);
    }

    @Override
    public void deleteAuthorsById(Long id) {
        Authors authors = authorsRepository.getAuthorsById(id);
        if (authors == null) {
            throw new ResourceNotFoundException("User with ID: " + id + " Not Found");
        }
        authors.setIsDeleted(true);
        authorsRepository.save(authors);
    }

}
