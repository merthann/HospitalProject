package com.example.hospital_management.controller;

import com.example.hospital_management.entities.Doctor;
import com.example.hospital_management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // GET: Retrieve all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() throws SQLException {
        return doctorService.getAllDoctors();
    }

    // GET: Retrieve a doctor by ID
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable long id) throws SQLException {
        return doctorService.getDoctorById(id);
    }

    // POST: Create a new doctor
    @PostMapping
    public void createDoctor(@RequestBody Doctor doctor) throws SQLException {
        doctorService.createDoctor(doctor);
    }

    // PUT: Update an existing doctor
    @PutMapping("/{id}")
    public void updateDoctor(@PathVariable long id, @RequestBody Doctor doctor) throws SQLException {
        doctorService.updateDoctor(id, doctor);
    }

    // DELETE: Remove a doctor by ID
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable long id) throws SQLException {
        doctorService.deleteDoctor(id);
    }
}
