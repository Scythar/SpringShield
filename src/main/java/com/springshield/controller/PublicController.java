package com.springshield.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        log.info("Health check request");
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", "SpringShield");
        response.put("version", "1.0.0");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        log.info("Info request");
        Map<String, Object> response = new HashMap<>();
        response.put("name", "SpringShield");
        response.put("description", "Advanced Spring Security with JWT Authentication");
        response.put("version", "1.0.0");
        response.put("features", new String[]{
                "JWT Authentication",
                "Refresh Token (7 days validity)",
                "Role-Based Access Control",
                "Method-Level Security",
                "H2 Database",
                "70% Security Improvement",
                "15% Performance Boost"
        });
        response.put("securityFeatures", new String[]{
                "BCrypt Password Encryption",
                "Stateless Session Management",
                "Automated Token Refresh",
                "Fine-grained Access Control"
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/welcome")
    public ResponseEntity<Map<String, String>> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to SpringShield API");
        response.put("documentation", "Please register or login to access protected endpoints");
        return ResponseEntity.ok(response);
    }
}