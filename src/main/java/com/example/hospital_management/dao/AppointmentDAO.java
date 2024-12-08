package com.example.hospital_management.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.hospital_management.entities.Appointment;

@Repository
public class AppointmentDAO {
    private final Connection connection;

    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    public Appointment getById(long id) throws SQLException {
        String query = "SELECT * FROM Appointment WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToAppointment(resultSet);
            }
        }
        return null;
    }

    public List<Appointment> getAll() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                appointments.add(mapRowToAppointment(resultSet));
            }
        }
        return appointments;
    }

    public void save(Appointment appointment) throws SQLException {
        String query = "INSERT INTO Appointment (patientid, doctorid, time, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, appointment.getPatientId());
            statement.setLong(2, appointment.getDoctorId());
            statement.setInt(3, appointment.getAppointmentTime());
            statement.setDate(4, Date.valueOf(appointment.getAppointmentDate()));
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                appointment.setId(keys.getLong(1));
            }
        }
    }

    public void update(Appointment appointment) throws SQLException {
        String query = "UPDATE Appointment SET patientid = ?, doctorid = ?, time = ?, date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, appointment.getPatientId());
            statement.setLong(2, appointment.getDoctorId());
            statement.setInt(3, appointment.getAppointmentTime());
            statement.setDate(4, Date.valueOf(appointment.getAppointmentDate()));
            statement.setLong(5, appointment.getId());
            statement.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String query = "DELETE FROM Appointment WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private Appointment mapRowToAppointment(ResultSet resultSet) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(resultSet.getLong("id"));
        appointment.setPatientId(resultSet.getLong("patientid"));
        appointment.setDoctorId(resultSet.getLong("doctorid"));
        appointment.setAppointmentTime(resultSet.getInt("time"));
        appointment.setAppointmentDate(resultSet.getDate("date").toLocalDate());
        return appointment;
    }
}
