package com.librarybe.librarybe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarybe.librarybe.controller.dto.request.CategoryRequest;
import com.librarybe.librarybe.controller.dto.response.ApiResponse;
import com.librarybe.librarybe.controller.dto.response.CategoryResponse;
import com.librarybe.librarybe.entity.Category;
import com.librarybe.librarybe.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/category")
@Validated
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryResponse> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", categories), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryByID(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", categoryResponse),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        Category categories = categoryService.savCategory(categoryRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Success Created", categories),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,
            @RequestBody @Valid CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success Updated", categoryResponse),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Success Deleted", null),
                HttpStatus.NO_CONTENT);
    }

}
