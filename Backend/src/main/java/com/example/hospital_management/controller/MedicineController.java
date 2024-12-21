package com.example.hospital_management.controller;

import com.example.hospital_management.entities.Medicine;
import com.example.hospital_management.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // GET: Retrieve all medicines
    @GetMapping
    public List<Medicine> getAllMedicines() throws SQLException {
        return medicineService.getAllMedicines();
    }

    // GET: Retrieve a medicine by ID
    @GetMapping("/{id}")
    public Medicine getMedicineById(@PathVariable long id) throws SQLException {
        return medicineService.getMedicineById(id);
    }

    // POST: Create a new medicine
    @PostMapping
    public void createMedicine(@RequestBody Medicine medicine) throws SQLException {
        medicineService.createMedicine(medicine);
    }

    // PUT: Update an existing medicine
    @PutMapping("/{id}")
    public void updateMedicine(@PathVariable long id, @RequestBody Medicine medicine) throws SQLException {
        medicineService.updateMedicine(id, medicine);
    }

    // DELETE: Remove a medicine by ID
    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable long id) throws SQLException {
        medicineService.deleteMedicine(id);
    }
}
