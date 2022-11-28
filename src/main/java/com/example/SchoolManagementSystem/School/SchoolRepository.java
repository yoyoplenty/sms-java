package com.example.SchoolManagementSystem.School;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface SchoolRepository extends JpaRepository<School, UUID> {
    Optional<School> findByEmail(String email);

    School findSchoolByEmail(String email);

    School findSchoolByName(String email);

    Optional<School> findSchoolById(UUID id);
}