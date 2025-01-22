package com.librarybe.librarybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.librarybe.librarybe.entity.Authors;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Long> {
    @Query(value = "select a from Authors a where a.isDeleted = false order by a.name")
    List<Authors> getAllAuthors();

    @Query(value = "select a from Authors a where a.id = :id and a.isDeleted = false")
    Authors getAuthorsById(Long id);
}
