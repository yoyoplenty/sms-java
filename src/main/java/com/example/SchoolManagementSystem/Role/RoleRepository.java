package com.example.SchoolManagementSystem.Role;


import com.example.SchoolManagementSystem.Enum.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Roles, UUID> {
    Optional<Roles> findRoleByName(EnumRole name);

    Optional<Roles> findByName(EnumRole enumRole);

    Optional<Roles> findRoleById(UUID id);

}
