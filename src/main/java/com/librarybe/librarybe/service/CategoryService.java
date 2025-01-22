package com.librarybe.librarybe.service;

import java.util.List;

import com.librarybe.librarybe.controller.dto.request.CategoryRequest;
import com.librarybe.librarybe.controller.dto.response.CategoryResponse;
import com.librarybe.librarybe.entity.Category;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();

    CategoryResponse getCategoryById(Long id);

    Category savCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);

    void deleteCategoryById(Long id);
}
