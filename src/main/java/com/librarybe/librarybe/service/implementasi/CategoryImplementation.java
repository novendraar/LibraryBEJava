package com.librarybe.librarybe.service.implementasi;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarybe.librarybe.controller.dto.request.CategoryRequest;
import com.librarybe.librarybe.controller.dto.response.CategoryResponse;
import com.librarybe.librarybe.entity.Category;
import com.librarybe.librarybe.exception.ResourceNotFoundException;
import com.librarybe.librarybe.repository.CategoryRepository;
import com.librarybe.librarybe.service.CategoryService;

@Service
public class CategoryImplementation implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public CategoryImplementation() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepository.getAllCategory();
        if (categories == null || categories.isEmpty()) {
            throw new ResourceNotFoundException("Data Not Found");
        }
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(src -> modelMapper.map(src, CategoryResponse.class)).collect(Collectors.toList());
        return categoryResponses;

    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.getCategoryById(id);
        if (category == null) {
            throw new ResourceNotFoundException("Category with ID: " + id + " Not Found");
        }
        CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
        return categoryResponse;
    }

    @Override
    public Category savCategory(CategoryRequest categoryRequest) {
        if (categoryRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        Category category = modelMapper.map(categoryRequest, Category.class);
        return categoryRepository.save(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.getCategoryById(id);
        if (category == null) {
            throw new ResourceNotFoundException("User with ID: " + id + " Not Found");
        }
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
        return categoryResponse;
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.getCategoryById(id);
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

}
