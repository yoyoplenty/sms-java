package com.example.SchoolManagementSystem.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Student findByStudentId(String id);

    Optional<Student> findStudentById(UUID id);

    Student findStudentByStudentId(String id);
}
