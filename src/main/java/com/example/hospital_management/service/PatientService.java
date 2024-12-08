package com.example.hospital_management.service;

import com.example.hospital_management.dao.PatientDAO;
import com.example.hospital_management.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientDAO patientDAO;

    @Autowired
    public PatientService(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    // Get all patients
    public List<Patient> getAllPatients() throws SQLException {
        return patientDAO.getAll();
    }

    // Get a patient by ID
    public Patient getPatientById(UUID id) throws SQLException {
        return patientDAO.getById(id);
    }

    // Add a new patient
    public void createPatient(Patient patient) throws SQLException {
        patient.setId(UUID.randomUUID()); // Generate a new UUID for the patient
        patientDAO.save(patient);
    }

    // Update an existing patient
    public void updatePatient(UUID id, Patient patient) throws SQLException {
        Patient existingPatient = patientDAO.getById(id);
        if (existingPatient != null) {
            existingPatient.setFname(patient.getFname());
            existingPatient.setLname(patient.getLname());
            existingPatient.setIdentityNo(patient.getIdentityNo());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setMobileNo(patient.getMobileNo());
            patientDAO.update(existingPatient);
        }
    }

    // Delete a patient by ID
    public void deletePatient(UUID id) throws SQLException {
        patientDAO.delete(id);
    }
}
