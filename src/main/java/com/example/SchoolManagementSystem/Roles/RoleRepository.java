package com.example.SchoolManagementSystem.Roles;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Roles, UUID> {
    Optional<Roles> findRoleByName(com.example.SchoolManagementSystem.Enum.Role name);

    Optional<Roles> findByName(com.example.SchoolManagementSystem.Enum.Role role);

    Optional<Roles> findRoleById(UUID id);

}
