package com.example.hospital_management.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital_management.dao.PoliclinicDAO;
import com.example.hospital_management.entities.Policlinic;

@Service
public class PoliclinicService {

    private final PoliclinicDAO policlinicDAO;

    @Autowired
    public PoliclinicService(PoliclinicDAO policlinicDAO) {
        this.policlinicDAO = policlinicDAO;
    }

    // Get all Policlinics
    public List<Policlinic> getAllPoliclinics() throws SQLException {
        return policlinicDAO.getAll();
    }

    // Get Policlinic by ID
    public Policlinic getPoliclinicById(long id) throws SQLException {
        return policlinicDAO.getById(id);
    }

    // Create Policlinic
    public void createPoliclinic(Policlinic policlinic) throws SQLException {
        policlinicDAO.save(policlinic);
    }

    // Update Policlinic
    public void updatePoliclinic(long id, Policlinic policlinic) throws SQLException {
        Policlinic existing = policlinicDAO.getById(id);
        if (existing != null) {
            existing.setName(policlinic.getName());
            existing.setFloor(policlinic.getFloor());
            policlinicDAO.update(existing);
        }
    }

    // Delete Policlinic
    public void deletePoliclinic(long id) throws SQLException {
        policlinicDAO.delete(id);
    }
}
