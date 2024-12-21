package com.example.hospital_management.controller;

import com.example.hospital_management.entities.Patient;
import com.example.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // GET: Retrieve all patients
    @GetMapping
    public List<Patient> getAllPatients() throws SQLException {
        return patientService.getAllPatients();
    }

    // GET: Retrieve a patient by ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable UUID id) throws SQLException {
        return patientService.getPatientById(id);
    }

    // POST: Create a new patient
    @PostMapping
    public void createPatient(@RequestBody Patient patient) throws SQLException {
        patientService.createPatient(patient);
    }

    // PUT: Update an existing patient
    @PutMapping("/{id}")
    public void updatePatient(@PathVariable UUID id, @RequestBody Patient patient) throws SQLException {
        patientService.updatePatient(id, patient);
    }

    // DELETE: Remove a patient by ID
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable UUID id) throws SQLException {
        patientService.deletePatient(id);
    }
}
