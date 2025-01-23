package com.librarybe.librarybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.librarybe.librarybe.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    @Query(value = "select p from Publisher p where p.isDeleted = false order by p.name")
    List<Publisher> getAllPublihser();

    @Query(value = "select p from Publisher p where p.id = :id and p.isDeleted = false")
    Publisher getPublisherById(Long id);
}
