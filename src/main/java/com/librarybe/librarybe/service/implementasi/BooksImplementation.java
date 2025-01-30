package com.librarybe.librarybe.service.implementasi;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarybe.librarybe.dto.request.BookRequest;
import com.librarybe.librarybe.dto.response.BookResponse;
import com.librarybe.librarybe.entity.Authors;
import com.librarybe.librarybe.entity.Books;
import com.librarybe.librarybe.entity.Category;
import com.librarybe.librarybe.entity.Publisher;
import com.librarybe.librarybe.exception.ResourceNotFoundException;
import com.librarybe.librarybe.repository.AuthorsRepository;
import com.librarybe.librarybe.repository.BookRepository;
import com.librarybe.librarybe.repository.CategoryRepository;
import com.librarybe.librarybe.repository.PublisherRepository;
import com.librarybe.librarybe.service.BookService;

@Service
public class BooksImplementation implements BookService {

    private final AuthorsRepository authorsRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    public BooksImplementation(AuthorsRepository authorsRepository,
            BookRepository bookRepository,
            CategoryRepository categoryRepository,
            PublisherRepository publisherRepository) {
        this.authorsRepository = authorsRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
        this.modelMapper = new ModelMapper();
    }

    private String notFound(String entity, Long id) {
        return entity + " with ID: " + id + " Not Found";
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Books> listBooks = bookRepository.getAllBooks();
        if (listBooks == null || listBooks.isEmpty()) {
            throw new ResourceNotFoundException("Data Not Found");
        }
        modelMapper.typeMap(Books.class, BookResponse.class).addMappings(book -> {
            book.map(src -> src.getAuthor().getName(), BookResponse::setAuthorsName);
            book.map(src -> src.getAuthor().getId(), BookResponse::setAuthorId);
            book.map(src -> src.getCategory().getName(), BookResponse::setCategoryName);
            book.map(src -> src.getCategory().getId(), BookResponse::setCategoryId);
            book.map(src -> src.getPublisher().getName(), BookResponse::setPublisherName);
            book.map(src -> src.getPublisher().getId(), BookResponse::setPublisherId);
        });

        return listBooks.stream().map(book -> modelMapper.map(book, BookResponse.class)).collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {
        Books book = bookRepository.getBooksById(id);
        if (book == null) {
            throw new ResourceNotFoundException(notFound("Book", id));
        }
        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        bookResponse.setAuthorsName(book.getAuthor().getName());
        bookResponse.setAuthorId(book.getAuthor().getId());
        bookResponse.setCategoryName(book.getCategory().getName());
        bookResponse.setCategoryId(book.getCategory().getId());
        bookResponse.setPublisherName(book.getPublisher().getName());
        bookResponse.setPublisherId(book.getPublisher().getId());
        return bookResponse;
    }

    @Override
    public Books saveBooks(BookRequest bookRequest) {
        if (bookRequest.getStock() == null) {
            bookRequest.setStock(0);
        }
        if (bookRequest.getPublishedAt() == null) {
            bookRequest.setPublishedAt(LocalDate.now());
        }
        Authors author = authorsRepository.getAuthorsById(bookRequest.getAuthorId());
        if (author == null) {
            throw new ResourceNotFoundException(notFound("Author", bookRequest.getAuthorId()));
        }
        Category category = categoryRepository.getCategoryById(bookRequest.getCategoryId());
        if (category == null) {
            throw new ResourceNotFoundException(notFound("Category", bookRequest.getCategoryId()));
        }
        Publisher publisher = publisherRepository.getPublisherById(bookRequest.getPublisherId());
        if (publisher == null) {
            throw new ResourceNotFoundException(notFound("Publisher", bookRequest.getPublisherId()));
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Books books = modelMapper.map(bookRequest, Books.class);
        books.setAuthor(author);
        books.setCategory(category);
        books.setPublisher(publisher);
        return bookRepository.save(books);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Books book = bookRepository.getBooksById(id);
        if (book == null) {
            throw new ResourceNotFoundException(notFound("Book", id));
        }
        Authors author = authorsRepository.getAuthorsById(bookRequest.getAuthorId());
        if (author == null) {
            throw new ResourceNotFoundException(notFound("Author", bookRequest.getAuthorId()));
        }
        Category category = categoryRepository.getCategoryById(bookRequest.getCategoryId());
        if (category == null) {
            throw new ResourceNotFoundException(notFound("Category", bookRequest.getCategoryId()));
        }
        Publisher publisher = publisherRepository.getPublisherById(bookRequest.getPublisherId());
        if (publisher == null) {
            throw new ResourceNotFoundException(notFound("Publisher", bookRequest.getPublisherId()));
        }
        if (bookRequest.getStock() != null) {
            book.setStock(bookRequest.getStock());
        }
        if (bookRequest.getPublishedAt() != null) {
            book.setPublishedAt(bookRequest.getPublishedAt());
            ;
        }
        if (bookRequest.getTitle() != null) {
            book.setTitle(bookRequest.getTitle());
        }
        if (bookRequest.getSynopsis() != null) {
            book.setSynopsis(bookRequest.getSynopsis());
        }
        book.setAuthor(author);
        book.setCategory(category);
        book.setPublisher(publisher);
        bookRepository.save(book);
        return modelMapper.map(book, BookResponse.class);

    }

    @Override
    public void deleteBookById(Long id) {
        Books books = bookRepository.getBooksById(id);
        if (books == null) {
            throw new ResourceNotFoundException(notFound("Book", id));
        }
        books.setIsDeleted(true);
        bookRepository.save(books);
    }

}
