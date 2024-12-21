package com.example.hospital_management.service;

import com.example.hospital_management.dao.AssistantDAO;
import com.example.hospital_management.entities.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AssistantService {

    private final AssistantDAO assistantDAO;

    @Autowired
    public AssistantService(AssistantDAO assistantDAO) {
        this.assistantDAO = assistantDAO;
    }

    // Get all Assistants
    public List<Assistant> getAllAssistants() throws SQLException {
        return assistantDAO.getAll();
    }

    // Get Assistant by ID
    public Assistant getAssistantById(long id) throws SQLException {
        return assistantDAO.getById(id);
    }

    // Create a new Assistant
    public void createAssistant(Assistant assistant) throws SQLException {
        assistantDAO.save(assistant);
    }

    // Update an Assistant
    public void updateAssistant(long id, Assistant assistant) throws SQLException {
        Assistant existingAssistant = assistantDAO.getById(id);
        if (existingAssistant != null) {
            existingAssistant.setFname(assistant.getFname());
            existingAssistant.setLname(assistant.getLname());
            existingAssistant.setWorkdays(assistant.getWorkdays());
            assistantDAO.update(existingAssistant);
        }
    }

    // Delete an Assistant by ID
    public void deleteAssistant(long id) throws SQLException {
        assistantDAO.delete(id);
    }
}
