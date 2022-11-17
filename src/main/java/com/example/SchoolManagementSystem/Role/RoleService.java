package com.example.SchoolManagementSystem.Role;


import com.example.SchoolManagementSystem.Enum.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
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

    public Roles findRoleByName(EnumRole name) {
        Optional<Roles> optionalRole = roleRepository.findRoleByName(name);
        if (optionalRole.isPresent()) return optionalRole.get();

        throw new IllegalStateException("role not found");
    }

    public Roles findRoleByTd(UUID id) {
        Optional<Roles> optionalRole = roleRepository.findRoleById(id);
        if (optionalRole.isPresent()) return optionalRole.get();

        throw new IllegalStateException("role with id " + id + " not found");
    }

    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }

    public Roles getRole(UUID id) {
        Optional<Roles> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) return optionalRole.get();

        throw new IllegalStateException("role not found");
    }

    public Roles UpdateRole(Roles role, UUID id) {
        Roles updatedRole = roleRepository.findById(id).orElseThrow(() -> new IllegalStateException("role not found"));

        updatedRole.setName(role.getName());

        return roleRepository.save(updatedRole);
    }

    public String DeleteRole(UUID id) {
        Roles role = roleRepository.findById(id).orElseThrow(() -> new IllegalStateException("role not found"));

        roleRepository.delete(role);
        return "deleted successfully";
    }
}
