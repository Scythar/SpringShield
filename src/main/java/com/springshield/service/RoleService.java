package com.springshield.service;

import com.springshield.dto.RoleDTO;
import com.springshield.model.Role;
import com.springshield.projection.RoleProjection;
import com.springshield.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDTO> getAllRoles() {
        log.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> {
                    RoleDTO dto = modelMapper.map(role, RoleDTO.class);
                    dto.setPermissions(role.getPermissions().stream()
                            .map(permission -> permission.getName())
                            .collect(Collectors.toSet()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleProjection> getAllRolesProjection() {
        log.info("Fetching all roles (projection)");
        return roleRepository.findAllProjectedBy();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public RoleDTO getRoleByName(String name) {
        log.info("Fetching role: {}", name);
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));

        RoleDTO dto = modelMapper.map(role, RoleDTO.class);
        dto.setPermissions(role.getPermissions().stream()
                .map(permission -> permission.getName())
                .collect(Collectors.toSet()));

        return dto;
    }
}