package com.example.hospital_management.controller;

import com.example.hospital_management.entities.Category;
import com.example.hospital_management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET: Retrieve all categories
    @GetMapping
    public List<Category> getAllCategories() throws SQLException {
        return categoryService.getAllCategories();
    }
}
