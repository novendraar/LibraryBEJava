package com.librarybe.librarybe.service;

import java.util.List;

import com.librarybe.librarybe.dto.request.PublisherRequest;
import com.librarybe.librarybe.dto.response.PublisherResponse;
import com.librarybe.librarybe.entity.Publisher;

public interface PublisherService {
    List<PublisherResponse> getAllPublisher();

    PublisherResponse getPublisherById(Long id);

    Publisher savePublisher(PublisherRequest publisherRequest);

    PublisherResponse updatePublisher(Long id, PublisherRequest publisherRequest);

    void deletePublisherById(Long id);
}
