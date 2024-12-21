package com.example.hospital_management.dao;

import com.example.hospital_management.entities.Category;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAO {
    private final Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public Category getById(long id) throws SQLException {
        String query = "SELECT * FROM Category WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToCategory(resultSet);
            }
        }
        return null;
    }

    // Retrieve all categories
    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(mapRowToCategory(resultSet));
            }
        }
        return categories;
    }
    
    private Category mapRowToCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
