package com.example.hospital_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.hospital_management.entities.Policlinic;

@Repository
public class PoliclinicDAO {
    private final Connection connection;

    public PoliclinicDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new Policlinic
    public void save(Policlinic policlinic) throws SQLException {
        String query = "INSERT INTO Policlinic (name, floor) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, policlinic.getName());
            statement.setInt(2, policlinic.getFloor());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                policlinic.setId(keys.getLong(1));
            }
        }
    }

    // Get Policlinic by ID
    public Policlinic getById(long id) throws SQLException {
        String query = "SELECT * FROM Policlinic WHERE policlinicid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToPoliclinic(resultSet);
            }
        }
        return null;
    }

    // Get all Policlinics
    public List<Policlinic> getAll() throws SQLException {
        List<Policlinic> policlinics = new ArrayList<>();
        String query = "SELECT * FROM Policlinic";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                policlinics.add(mapRowToPoliclinic(resultSet));
            }
        }
        return policlinics;
    }

    // Update a Policlinic
    public void update(Policlinic policlinic) throws SQLException {
        String query = "UPDATE Policlinic SET name = ?, floor = ? WHERE policlinicid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, policlinic.getName());
            statement.setInt(2, policlinic.getFloor());
            statement.setLong(3, policlinic.getId());
            statement.executeUpdate();
        }
    }

    // Delete a Policlinic by ID
    public void delete(long id) throws SQLException {
        String query = "DELETE FROM Policlinic WHERE policlinicid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Helper method to map a ResultSet row to a Policlinic object
    private Policlinic mapRowToPoliclinic(ResultSet resultSet) throws SQLException {
        Policlinic policlinic = new Policlinic();
        policlinic.setId(resultSet.getLong("policlinicid"));
        policlinic.setName(resultSet.getString("name"));
        policlinic.setFloor(resultSet.getInt("floor"));
        return policlinic;
    }
}
