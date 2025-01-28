package com.librarybe.librarybe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarybe.librarybe.dto.request.PublisherRequest;
import com.librarybe.librarybe.dto.response.ApiResponse;
import com.librarybe.librarybe.dto.response.PublisherResponse;
import com.librarybe.librarybe.entity.Publisher;
import com.librarybe.librarybe.service.PublisherService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/publisher")
@Validated
public class PublisherController {

    @Autowired
    PublisherService publisherService;

    @GetMapping("")
    public ResponseEntity<?> getAllPublisher() {
        List<PublisherResponse> publishers = publisherService.getAllPublisher();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", publishers), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPublisherByID(@PathVariable Long id) {
        PublisherResponse publisherResponse = publisherService.getPublisherById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", publisherResponse),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> savePublisher(@RequestBody @Valid PublisherRequest publisherRequest) {
        Publisher publisher = publisherService.savePublisher(publisherRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Success Created", publisher),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable Long id,
            @RequestBody @Valid PublisherRequest publisherRequest) {
        PublisherResponse publisherResponse = publisherService.updatePublisher(id, publisherRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success Updated", publisherResponse),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisherById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Success Deleted", null),
                HttpStatus.NO_CONTENT);
    }

}
