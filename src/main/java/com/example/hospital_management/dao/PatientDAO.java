package com.example.hospital_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.hospital_management.entities.Patient;

@Repository
public class PatientDAO {
    private final Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new Patient
    public void save(Patient patient) throws SQLException {
        String query = "INSERT INTO Patient (id, fname, lname, identityno, email, mobileno) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, patient.getId());
            statement.setString(2, patient.getFname());
            statement.setString(3, patient.getLname());
            statement.setLong(4, patient.getIdentityNo());
            statement.setString(5, patient.getEmail());
            statement.setLong(6, patient.getMobileNo());
            statement.executeUpdate();
        }
    }

    // Get Patient by ID
    public Patient getById(UUID id) throws SQLException {
        String query = "SELECT * FROM Patient WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToPatient(resultSet);
            }
        }
        return null;
    }

    // Get all Patients
    public List<Patient> getAll() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                patients.add(mapRowToPatient(resultSet));
            }
        }
        return patients;
    }

    // Update a Patient
    public void update(Patient patient) throws SQLException {
        String query = "UPDATE Patient SET fname = ?, lname = ?, identityno = ?, email = ?, mobileno = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getFname());
            statement.setString(2, patient.getLname());
            statement.setLong(3, patient.getIdentityNo());
            statement.setString(4, patient.getEmail());
            statement.setLong(5, patient.getMobileNo());
            statement.setObject(6, patient.getId());
            statement.executeUpdate();
        }
    }

    // Delete a Patient by ID
    public void delete(UUID id) throws SQLException {
        String query = "DELETE FROM Patient WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        }
    }

    // Helper method to map a ResultSet row to a Patient object
    private Patient mapRowToPatient(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setId(UUID.fromString(resultSet.getString("id")));
        patient.setFname(resultSet.getString("fname"));
        patient.setLname(resultSet.getString("lname"));
        patient.setIdentityNo(resultSet.getLong("identityno"));
        patient.setEmail(resultSet.getString("email"));
        patient.setMobileNo(resultSet.getLong("mobileno"));
        return patient;
    }
}
