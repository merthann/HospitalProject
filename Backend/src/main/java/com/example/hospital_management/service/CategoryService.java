package com.example.hospital_management.service;

import com.example.hospital_management.dao.CategoryDAO;
import com.example.hospital_management.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    // Get all categories
    public List<Category> getAllCategories() throws SQLException {
        return categoryDAO.getAll();
    }

    // Get a category by ID
    public Category getCategoryById(long id) throws SQLException {
        return categoryDAO.getById(id);
    }

}
