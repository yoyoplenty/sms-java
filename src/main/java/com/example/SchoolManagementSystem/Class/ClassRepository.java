package com.example.SchoolManagementSystem.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {
    Optional<Class> findClassById(UUID id);

    List<Class> findClassBySchoolId(UUID id);
}
