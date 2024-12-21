package com.example.hospital_management.service;

import com.example.hospital_management.dao.AppointmentDAO;
import com.example.hospital_management.entities.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentDAO appointmentDAO;

    @Autowired
    public AppointmentService(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentDAO.getAll();
    }

    // Get an appointment by ID
    public Appointment getAppointmentById(long id) throws SQLException {
        return appointmentDAO.getById(id);
    }

    // Create a new appointment
    public void createAppointment(Appointment appointment) throws SQLException {
        appointmentDAO.save(appointment);
    }

    // Update an existing appointment
    public void updateAppointment(long id, Appointment appointment) throws SQLException {
        Appointment existingAppointment = appointmentDAO.getById(id);
        if (existingAppointment != null) {
            existingAppointment.setPatientId(appointment.getPatientId());
            existingAppointment.setDoctorId(appointment.getDoctorId());
            existingAppointment.setAppointmentTime(appointment.getAppointmentTime());
            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
            appointmentDAO.update(existingAppointment);
        }
    }

    // Delete an appointment by ID
    public void deleteAppointment(long id) throws SQLException {
        appointmentDAO.delete(id);
    }
}
