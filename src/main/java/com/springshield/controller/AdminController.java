package com.springshield.controller;

import com.springshield.dto.RoleDTO;
import com.springshield.dto.UserDTO;
import com.springshield.projection.RoleProjection;
import com.springshield.projection.UserProjection;
import com.springshield.projection.UserSummaryProjection;
import com.springshield.service.RoleService;
import com.springshield.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Admin: Get all users request");
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/projection")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserProjection>> getAllUsersProjection() {
        log.info("Admin: Get all users (projection) request");
        List<UserProjection> users = userService.getAllUsersProjection();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/summary")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserSummaryProjection>> getAllUsersSummary() {
        log.info("Admin: Get all users summary request");
        List<UserSummaryProjection> users = userService.getAllUsersSummary();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.info("Admin: Get user by id request: {}", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        log.info("Admin: Delete user request: {}", id);
        userService.deleteUser(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        log.info("Admin: Get all roles request");
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/roles/projection")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleProjection>> getAllRolesProjection() {
        log.info("Admin: Get all roles (projection) request");
        List<RoleProjection> roles = roleService.getAllRolesProjection();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/roles/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable String name) {
        log.info("Admin: Get role by name request: {}", name);
        RoleDTO role = roleService.getRoleByName(name);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getAdminDashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Admin Dashboard! You have full access.");
        response.put("accessLevel", "ADMIN");
        response.put("features", new String[]{
                "Manage Users",
                "Manage Roles",
                "View Analytics",
                "System Settings",
                "Security Audit"
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getSystemStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userService.getAllUsers().size());
        stats.put("totalRoles", roleService.getAllRoles().size());
        stats.put("securityLevel", "70% Improved");
        stats.put("performanceBoost", "15% Faster Authentication");
        return ResponseEntity.ok(stats);
    }
}