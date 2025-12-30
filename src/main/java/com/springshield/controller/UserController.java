package com.springshield.controller;

import com.springshield.dto.UserDTO;
import com.springshield.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        log.info("Get current user request");
        UserDTO user = userService.getCurrentUser(authentication.getName());
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO,
            Authentication authentication) {
        log.info("Update user request for id: {}", id);
        UserDTO updatedUser = userService.updateUser(id, userDTO, authentication.getName());
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, String>> getUserProfile(Authentication authentication) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome " + authentication.getName() + "! You have USER access.");
        response.put("username", authentication.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<String, Object>> getUserDashboard(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User Dashboard");
        response.put("username", authentication.getName());
        response.put("accessLevel", "USER");
        response.put("features", new String[]{
                "View Profile",
                "Update Profile",
                "Change Password"
        });
        return ResponseEntity.ok(response);
    }
}