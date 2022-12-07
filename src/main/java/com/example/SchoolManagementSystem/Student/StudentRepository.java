package com.example.SchoolManagementSystem.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByStudentId(String id);

    Optional<Student> findStudentById(UUID id);

    Student findStudentByStudentId(String id);

    Optional<Student> findStudentByFirstNameAndLastNameAndMiddleName
            (String firstName, String lastName, String middleName);

    Student findByFirstNameAndLastNameAndMiddleName
            (String firstName, String lastName, String middleName);

    List<Student> findStudentBySchoolId(UUID schoolId);
}
