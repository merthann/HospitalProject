package com.example.hospital_management.controller;

import com.example.hospital_management.entities.Appointment;
import com.example.hospital_management.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // GET: Retrieve all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentService.getAllAppointments();
    }

    // GET: Retrieve an appointment by ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable long id) throws SQLException {
        return appointmentService.getAppointmentById(id);
    }

    // POST: Create a new appointment
    @PostMapping
    public void createAppointment(@RequestBody Appointment appointment) throws SQLException {
        appointmentService.createAppointment(appointment);
    }

    // PUT: Update an existing appointment
    @PutMapping("/{id}")
    public void updateAppointment(@PathVariable long id, @RequestBody Appointment appointment) throws SQLException {
        appointmentService.updateAppointment(id, appointment);
    }

    // DELETE: Remove an appointment by ID
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable long id) throws SQLException {
        appointmentService.deleteAppointment(id);
    }
}
