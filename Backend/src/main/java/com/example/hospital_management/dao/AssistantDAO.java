package com.example.hospital_management.dao;

import com.example.hospital_management.entities.Assistant;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AssistantDAO {
    private final Connection connection;

    public AssistantDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new Assistant
    public void save(Assistant assistant) throws SQLException {
        String query = "INSERT INTO Assistant (fname, lname, workdays) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, assistant.getFname());
            statement.setString(2, assistant.getLname());
            statement.setString(3, assistant.getWorkdays());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                assistant.setId(keys.getLong(1));
            }
        }
    }

    // Get Assistant by ID
    public Assistant getById(long id) throws SQLException {
        String query = "SELECT * FROM Assistant WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToAssistant(resultSet);
            }
        }
        return null;
    }

    // Get all Assistants
    public List<Assistant> getAll() throws SQLException {
        List<Assistant> assistants = new ArrayList<>();
        String query = "SELECT * FROM Assistant";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                assistants.add(mapRowToAssistant(resultSet));
            }
        }
        return assistants;
    }

    // Update an Assistant
    public void update(Assistant assistant) throws SQLException {
        String query = "UPDATE Assistant SET fname = ?, lname = ?, workdays = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, assistant.getFname());
            statement.setString(2, assistant.getLname());
            statement.setString(3, assistant.getWorkdays());
            statement.setLong(4, assistant.getId());
            statement.executeUpdate();
        }
    }

    // Delete an Assistant by ID
    public void delete(long id) throws SQLException {
        String query = "DELETE FROM Assistant WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Helper method to map a ResultSet row to an Assistant object
    private Assistant mapRowToAssistant(ResultSet resultSet) throws SQLException {
        Assistant assistant = new Assistant();
        assistant.setId(resultSet.getLong("id"));
        assistant.setFname(resultSet.getString("fname"));
        assistant.setLname(resultSet.getString("lname"));
        assistant.setWorkdays(resultSet.getString("workdays"));
        return assistant;
    }
}
