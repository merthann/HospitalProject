package com.example.hospital_management.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital_management.entities.Policlinic;
import com.example.hospital_management.service.PoliclinicService;

@RestController
@RequestMapping("/policlinic")
public class PoliclinicController {

    private final PoliclinicService policlinicService;

    @Autowired
    public PoliclinicController(PoliclinicService policlinicService) {
        this.policlinicService = policlinicService;
    }

    // GET: Retrieve all policlinics
    @GetMapping
    public List<Policlinic> getAllPoliclinics() throws SQLException {
        return policlinicService.getAllPoliclinics();
    }

    // GET: Retrieve a specific policlinic by ID
    @GetMapping("/{id}")
    public Policlinic getPoliclinicById(@PathVariable long id) throws SQLException {
        return policlinicService.getPoliclinicById(id);
    }

    // POST: Create a new policlinic
    @PostMapping
    public void createPoliclinic(@RequestBody Policlinic policlinic) throws SQLException {
        policlinicService.createPoliclinic(policlinic);
    }

    // PUT: Update an existing policlinic
    @PutMapping("/{id}")
    public void updatePoliclinic(@PathVariable long id, @RequestBody Policlinic policlinic) throws SQLException {
        policlinicService.updatePoliclinic(id, policlinic);
    }

    // DELETE: Remove a policlinic by ID
    @DeleteMapping("/{id}")
    public void deletePoliclinic(@PathVariable long id) throws SQLException {
        policlinicService.deletePoliclinic(id);
    }
}
