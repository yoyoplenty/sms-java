package com.example.SchoolManagementSystem.Roles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

@Controller
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Roles createRole(Roles role) {
        Optional<Roles> optionalRole = roleRepository.findRoleByName(role.getName());

        if (optionalRole.isPresent()) throw new IllegalStateException("role already exist");
        return roleRepository.save(role);
    }

    public Roles findRoleByName(com.example.SchoolManagementSystem.Enum.Role name) {
        Optional<Roles> optionalRole = roleRepository.findRoleByName(name);
        if (optionalRole.isPresent()) return optionalRole.get();

        throw new IllegalStateException("role not found");
    }

    public Roles findRoleByTd(UUID id) {
        Optional<Roles> optionalRole = roleRepository.findRoleById(id);
        if (optionalRole.isPresent()) return optionalRole.get();

        throw new IllegalStateException("role with id " + id + " not found");
    }
}
