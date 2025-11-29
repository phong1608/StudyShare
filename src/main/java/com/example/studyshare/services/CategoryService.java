package com.example.studyshare.services;


import com.example.studyshare.entities.Category;
import com.example.studyshare.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category Create(Category category) {
        return categoryRepository.save(category);
    }
}
