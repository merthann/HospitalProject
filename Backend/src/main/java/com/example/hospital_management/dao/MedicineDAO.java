package com.example.hospital_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.hospital_management.entities.Category;
import com.example.hospital_management.entities.Medicine;

@Repository
public class MedicineDAO {
    private final Connection connection;

    public MedicineDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new Medicine
    public void save(Medicine medicine) throws SQLException {
        String query = "INSERT INTO Medicine (name, description, category) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getDescription());
            statement.setLong(3, medicine.getCategory().getId()); // Use category ID as the foreign key
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                medicine.setId(keys.getLong(1));
            }
        }
    }


    // Get Medicine by ID
    public Medicine getById(long id) throws SQLException {
        String query = """
                SELECT m.id, m.name, m.description, c.id AS category_id, c.name AS category_name
                FROM Medicine m
                JOIN Category c ON m.category = c.id
                WHERE m.id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToMedicine(resultSet);
            }
        }
        return null;
    }


    // Get all Medicines
    public List<Medicine> getAll() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String query = """
                SELECT m.id, m.name, m.description, c.id AS category_id, c.name AS category_name
                FROM Medicine m
                JOIN Category c ON m.category = c.id
                """;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                medicines.add(mapRowToMedicine(resultSet));
            }
        }
        return medicines;
    }

    // Update a Medicine
    public void update(Medicine medicine) throws SQLException {
        String query = "UPDATE Medicine SET name = ?, description = ?, category = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getDescription());
            statement.setLong(3, medicine.getCategory().getId()); // Use category ID as the foreign key
            statement.setLong(4, medicine.getId());
            statement.executeUpdate();
        }
    }

    // Delete a Medicine by ID
    public void delete(long id) throws SQLException {
        String query = "DELETE FROM Medicine WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Helper method to map a ResultSet row to a Medicine object
    private Medicine mapRowToMedicine(ResultSet resultSet) throws SQLException {
        Medicine medicine = new Medicine();
        medicine.setId(resultSet.getLong("id"));
        medicine.setName(resultSet.getString("name"));
        medicine.setDescription(resultSet.getString("description"));

        // Create and set the Category object
        Category category = new Category();
        category.setId(resultSet.getLong("category_id"));
        category.setName(resultSet.getString("category_name"));

        medicine.setCategory(category);
        return medicine;
    }
}
