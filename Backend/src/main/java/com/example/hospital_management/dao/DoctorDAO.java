package com.example.hospital_management.dao;

import com.example.hospital_management.entities.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DoctorDAO {
    private final Connection connection;

    public DoctorDAO(Connection connection) {
        this.connection = connection;
    }

    // Get a doctor by ID
    public Doctor getById(long id) throws SQLException {
        String query = "SELECT * FROM Doctor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToDoctor(resultSet);
            }
        }
        return null;
    }

    // Get all doctors
    public List<Doctor> getAll() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM Doctor";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                doctors.add(mapRowToDoctor(resultSet));
            }
        }
        return doctors;
    }

    // Add a new doctor
    public void save(Doctor doctor) throws SQLException {
        String query = "INSERT INTO Doctor (title, fname, lname, policlinicid, assistantid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, doctor.getTitle());
            statement.setString(2, doctor.getFname());
            statement.setString(3, doctor.getLname());
            statement.setLong(4, doctor.getPoliclinicId());
            statement.setLong(5, doctor.getAssistantId());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                doctor.setId(keys.getLong(1)); // Set generated ID
            }
        }
    }

    // Update an existing doctor
    public void update(Doctor doctor) throws SQLException {
        String query = "UPDATE Doctor SET title = ?, fname = ?, lname = ?, policlinicid = ?, assistantid = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getTitle());
            statement.setString(2, doctor.getFname());
            statement.setString(3, doctor.getLname());
            statement.setLong(4, doctor.getPoliclinicId());
            statement.setLong(5, doctor.getAssistantId());
            statement.setLong(6, doctor.getId());
            statement.executeUpdate();
        }
    }

    // Delete a doctor by ID
    public void delete(long id) throws SQLException {
        String query = "DELETE FROM Doctor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Helper method to map a database row to a Doctor object
    private Doctor mapRowToDoctor(ResultSet resultSet) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(resultSet.getLong("id"));
        doctor.setTitle(resultSet.getString("title"));
        doctor.setFname(resultSet.getString("fname"));
        doctor.setLname(resultSet.getString("lname"));
        doctor.setPoliclinicId(resultSet.getLong("policlinicid"));
        doctor.setAssistantId(resultSet.getLong("assistantid"));
        return doctor;
    }
}
