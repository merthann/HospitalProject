package com.example.hospital_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Bean
    public Connection connection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Hospital";
        String username = "postgres";
        String password = "P801600302e"; // Replace with your PostgreSQL password

        return DriverManager.getConnection(url, username, password);
    }
}
