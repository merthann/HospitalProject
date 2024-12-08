package com.example.hospital_management.service;

import com.example.hospital_management.dao.TestDAO;
import com.example.hospital_management.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TestService {

    private final TestDAO testDAO;

    @Autowired
    public TestService(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    // Get all tests
    public List<Test> getAllTests() throws SQLException {
        return testDAO.getAll();
    }

    // Get a test by ID
    public Test getTestById(long id) throws SQLException {
        return testDAO.getById(id);
    }

    // Create a new test
    public void createTest(Test test) throws SQLException {
        testDAO.save(test);
    }

    // Update an existing test
    public void updateTest(long id, Test test) throws SQLException {
        Test existingTest = testDAO.getById(id);
        if (existingTest != null) {
            existingTest.setName(test.getName());
            existingTest.setResults(test.getResults());
            testDAO.update(existingTest);
        }
    }

    // Delete a test by ID
    public void deleteTest(long id) throws SQLException {
        testDAO.delete(id);
    }
}
