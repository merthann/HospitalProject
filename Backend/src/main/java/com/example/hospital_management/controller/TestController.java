package com.example.hospital_management.controller;

import com.example.hospital_management.entities.Test;
import com.example.hospital_management.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    // GET: Retrieve all tests
    @GetMapping
    public List<Test> getAllTests() throws SQLException {
        return testService.getAllTests();
    }

    // GET: Retrieve a test by ID
    @GetMapping("/{id}")
    public Test getTestById(@PathVariable long id) throws SQLException {
        return testService.getTestById(id);
    }

    // POST: Create a new test
    @PostMapping
    public void createTest(@RequestBody Test test) throws SQLException {
        testService.createTest(test);
    }

    // PUT: Update an existing test
    @PutMapping("/{id}")
    public void updateTest(@PathVariable long id, @RequestBody Test test) throws SQLException {
        testService.updateTest(id, test);
    }

    // DELETE: Remove a test by ID
    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable long id) throws SQLException {
        testService.deleteTest(id);
    }
}
