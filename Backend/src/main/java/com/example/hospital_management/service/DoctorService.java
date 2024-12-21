package com.example.hospital_management.service;

import com.example.hospital_management.dao.DoctorDAO;
import com.example.hospital_management.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorDAO doctorDAO;

    @Autowired
    public DoctorService(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() throws SQLException {
        return doctorDAO.getAll();
    }

    // Get a doctor by ID
    public Doctor getDoctorById(long id) throws SQLException {
        return doctorDAO.getById(id);
    }

    // Add a new doctor
    public void createDoctor(Doctor doctor) throws SQLException {
        doctorDAO.save(doctor);
    }

    // Update an existing doctor
    public void updateDoctor(long id, Doctor doctor) throws SQLException {
        Doctor existingDoctor = doctorDAO.getById(id);
        if (existingDoctor != null) {
            existingDoctor.setTitle(doctor.getTitle());
            existingDoctor.setFname(doctor.getFname());
            existingDoctor.setLname(doctor.getLname());
            existingDoctor.setPoliclinicId(doctor.getPoliclinicId());
            existingDoctor.setAssistantId(doctor.getAssistantId());
            doctorDAO.update(existingDoctor);
        }
    }

    // Delete a doctor by ID
    public void deleteDoctor(long id) throws SQLException {
        doctorDAO.delete(id);
    }
}
