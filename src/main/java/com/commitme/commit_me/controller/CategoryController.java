package com.commitme.commit_me.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commitme.commit_me.model.Category;
import com.commitme.commit_me.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/categories")

public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

}
