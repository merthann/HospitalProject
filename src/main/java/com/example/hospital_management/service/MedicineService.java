package com.example.hospital_management.service;

import com.example.hospital_management.dao.MedicineDAO;
import com.example.hospital_management.entities.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MedicineService {

    private final MedicineDAO medicineDAO;

    @Autowired
    public MedicineService(MedicineDAO medicineDAO) {
        this.medicineDAO = medicineDAO;
    }

    // Get all medicines
    public List<Medicine> getAllMedicines() throws SQLException {
        return medicineDAO.getAll();
    }

    // Get a medicine by ID
    public Medicine getMedicineById(long id) throws SQLException {
        return medicineDAO.getById(id);
    }

    // Create a new medicine
    public void createMedicine(Medicine medicine) throws SQLException {
        medicineDAO.save(medicine);
    }

    // Update an existing medicine
    public void updateMedicine(long id, Medicine medicine) throws SQLException {
        Medicine existingMedicine = medicineDAO.getById(id);
        if (existingMedicine != null) {
            existingMedicine.setName(medicine.getName());
            existingMedicine.setDescription(medicine.getDescription());
            existingMedicine.setCategory(medicine.getCategory());
            medicineDAO.update(existingMedicine);
        }
    }

    // Delete a medicine by ID
    public void deleteMedicine(long id) throws SQLException {
        medicineDAO.delete(id);
    }
}
