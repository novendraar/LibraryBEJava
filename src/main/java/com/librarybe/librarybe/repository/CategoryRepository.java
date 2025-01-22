package com.librarybe.librarybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.librarybe.librarybe.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select c from Category c where c.isDeleted = false order by c.name")
    List<Category> getAllCategory();

    @Query(value = "select c from Category c where c.id = :id and c.isDeleted = false ")
    Category getCategoryById(Long id);
}
