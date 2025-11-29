package com.example.studyshare.controller;

import com.example.studyshare.entities.Category;
import com.example.studyshare.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category){
        return categoryService.Create(category);
    }
    @GetMapping("/all")
    public List<Category> getAllCategory(){
        return categoryService.findAll();
    }


}
