package com.librarybe.librarybe.service.implementasi;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarybe.librarybe.dto.request.PublisherRequest;
import com.librarybe.librarybe.dto.response.PublisherResponse;
import com.librarybe.librarybe.entity.Publisher;
import com.librarybe.librarybe.exception.ResourceNotFoundException;
import com.librarybe.librarybe.repository.PublisherRepository;
import com.librarybe.librarybe.service.PublisherService;

@Service
public class PublisherImplementation implements PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    private final ModelMapper modelMapper;

    private String notFound(Long id) {
        return "Publisher with ID: " + id + " Not Found";
    }

    public PublisherImplementation() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<PublisherResponse> getAllPublisher() {
        List<Publisher> publishers = publisherRepository.getAllPublihser();
        if (publishers == null || publishers.isEmpty()) {
            throw new ResourceNotFoundException("Data Not Found");
        }
        return publishers.stream().map(src -> modelMapper.map(src, PublisherResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PublisherResponse getPublisherById(Long id) {
        Publisher publisher = publisherRepository.getPublisherById(id);
        if (publisher == null) {
            throw new ResourceNotFoundException(notFound(id));
        }
        return modelMapper.map(publisher, PublisherResponse.class);
    }

    @Override
    public Publisher savePublisher(PublisherRequest publisherRequest) {
        if (publisherRequest.getName().isEmpty() || publisherRequest.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        Publisher publisher = modelMapper.map(publisherRequest, Publisher.class);
        return publisherRepository.save(publisher);
    }

    @Override
    public PublisherResponse updatePublisher(Long id, PublisherRequest publisherRequest) {
        Publisher publisher = publisherRepository.getPublisherById(id);
        if (publisher == null) {
            throw new ResourceNotFoundException(notFound(id));
        }
        if (publisherRequest.getName().isEmpty() || publisherRequest.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Fields address / name cannot be empty");
        }
        publisher.setName(publisherRequest.getName());
        publisher.setAddress(publisherRequest.getAddress());
        publisherRepository.save(publisher);
        return modelMapper.map(publisher, PublisherResponse.class);

    }

    @Override
    public void deletePublisherById(Long id) {
        Publisher publisher = publisherRepository.getPublisherById(id);
        if (publisher == null) {
            throw new ResourceNotFoundException(notFound(id));
        }
        publisher.setIsDeleted(true);
        publisherRepository.save(publisher);
    }

}
