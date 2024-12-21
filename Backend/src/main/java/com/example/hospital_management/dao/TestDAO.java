package com.example.hospital_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.hospital_management.entities.Test;

@Repository
public class TestDAO {
    private final Connection connection;

    public TestDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new Test
    public void save(Test test) throws SQLException {
        String query = "INSERT INTO Test (name, results) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, test.getName());
            statement.setString(2, test.getResults());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                test.setId(keys.getLong(1));
            }
        }
    }

    // Get Test by ID
    public Test getById(long id) throws SQLException {
        String query = "SELECT * FROM Test WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToTest(resultSet);
            }
        }
        return null;
    }

    // Get all Tests
    public List<Test> getAll() throws SQLException {
        List<Test> tests = new ArrayList<>();
        String query = "SELECT * FROM Test";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tests.add(mapRowToTest(resultSet));
            }
        }
        return tests;
    }

    // Update a Test
    public void update(Test test) throws SQLException {
        String query = "UPDATE Test SET name = ?, results = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, test.getName());
            statement.setString(2, test.getResults());
            statement.setLong(3, test.getId());
            statement.executeUpdate();
        }
    }

    // Delete a Test by ID
    public void delete(long id) throws SQLException {
        String query = "DELETE FROM Test WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Helper method to map a ResultSet row to a Test object
    private Test mapRowToTest(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        test.setId(resultSet.getLong("id"));
        test.setName(resultSet.getString("name"));
        test.setResults(resultSet.getString("results"));
        return test;
    }
}
