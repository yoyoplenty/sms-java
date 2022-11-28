package com.example.SchoolManagementSystem.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Admin findAdminById(UUID uuid);

    Optional<Admin> findAdminByStaffId(String staffId);

    Optional<Admin> findAdminByUserId(UUID userId);
}

