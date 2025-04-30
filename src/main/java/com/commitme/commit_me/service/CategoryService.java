package com.commitme.commit_me.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.commitme.commit_me.exceptions.CategoryAlreadyExistsException;
import com.commitme.commit_me.model.Category;
import com.commitme.commit_me.repository.CategoryRepository;

@Service

public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> createCategory(Category category) {

        if (categoryRepository.findByCategory(category.getType()) != null) {
            throw new CategoryAlreadyExistsException("(!) ERROR: ya existe una categoría con el mismo nombre");
        }

        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }

}
