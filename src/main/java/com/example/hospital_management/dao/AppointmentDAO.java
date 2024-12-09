package com.example.hospital_management.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            statement.setObject(1, appointment.getPatientId(), Types.OTHER); // Set UUID as Object
            statement.setLong(2, appointment.getDoctorId());
            statement.setTime(3, Time.valueOf(appointment.getAppointmentTime())); // Set LocalTime
            statement.setDate(4, Date.valueOf(appointment.getAppointmentDate())); // Set LocalDate
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
            statement.setObject(1, appointment.getPatientId(), Types.OTHER); // Set UUID as Object
            statement.setLong(2, appointment.getDoctorId());
            statement.setTime(3, Time.valueOf(appointment.getAppointmentTime())); // Set LocalTime
            statement.setDate(4, Date.valueOf(appointment.getAppointmentDate())); // Set LocalDate
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
        appointment.setPatientId(UUID.fromString(resultSet.getString("patientid"))); // Map UUID
        appointment.setDoctorId(resultSet.getLong("doctorid"));
        appointment.setAppointmentTime(resultSet.getTime("time").toLocalTime()); // Map SQL Time to LocalTime
        appointment.setAppointmentDate(resultSet.getDate("date").toLocalDate()); // Map SQL Date to LocalDate
        return appointment;
    }
}
