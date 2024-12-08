package com.example.hospital_management.controller;

import com.example.hospital_management.entities.Assistant;
import com.example.hospital_management.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/assistant")
public class AssistantController {

    private final AssistantService assistantService;

    @Autowired
    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    // GET: Retrieve all Assistants
    @GetMapping
    public List<Assistant> getAllAssistants() throws SQLException {
        return assistantService.getAllAssistants();
    }

    // GET: Retrieve an Assistant by ID
    @GetMapping("/{id}")
    public Assistant getAssistantById(@PathVariable long id) throws SQLException {
        return assistantService.getAssistantById(id);
    }

    // POST: Create a new Assistant
    @PostMapping
    public void createAssistant(@RequestBody Assistant assistant) throws SQLException {
        assistantService.createAssistant(assistant);
    }

    // PUT: Update an existing Assistant
    @PutMapping("/{id}")
    public void updateAssistant(@PathVariable long id, @RequestBody Assistant assistant) throws SQLException {
        assistantService.updateAssistant(id, assistant);
    }

    // DELETE: Remove an Assistant by ID
    @DeleteMapping("/{id}")
    public void deleteAssistant(@PathVariable long id) throws SQLException {
        assistantService.deleteAssistant(id);
    }
}
