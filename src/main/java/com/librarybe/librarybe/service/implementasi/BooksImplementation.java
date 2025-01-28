package com.librarybe.librarybe.service.implementasi;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarybe.librarybe.dto.request.BookRequest;
import com.librarybe.librarybe.dto.response.BookResponse;
import com.librarybe.librarybe.entity.Books;
import com.librarybe.librarybe.repository.AuthorsRepository;
import com.librarybe.librarybe.repository.BookRepository;
import com.librarybe.librarybe.repository.CategoryRepository;
import com.librarybe.librarybe.repository.PublisherRepository;
import com.librarybe.librarybe.service.BookService;

@Service
public class BooksImplementation implements BookService {

    public BooksImplementation() {
        this.modelMapper = new ModelMapper();
    }

    private final ModelMapper modelMapper;

    @Autowired
    AuthorsRepository authorsRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public List<BookResponse> getAllBooks() {
        throw new UnsupportedOperationException("Unimplemented method 'getBookById'");
    }

    @Override
    public BookResponse getBookById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookById'");
    }

    @Override
    public Books saveBooks(BookRequest bookRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveBooks'");
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBook'");
    }

    @Override
    public void deleteBookById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBookById'");
    }

}
