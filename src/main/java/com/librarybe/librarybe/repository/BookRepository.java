package com.librarybe.librarybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.librarybe.librarybe.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "select b from Books b where b.isDeleted = false order by b.title")
    List<Books> getAllBooks();

    @Query(value = "select b from Books b where b.id = :id and b.isDeleted = false")
    Books getBooksById(Long id);
}
