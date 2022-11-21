package com.example.SchoolManagementSystem.Subject;

import com.example.SchoolManagementSystem.Enum.EnumGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findSubjectById(UUID uuid);

    Optional<Subject> findSubjectByName(String name);

    Optional<Subject> findSubjectByCode(String code);

    Optional<Subject> findSubjectByNameAndCode(String name, String code);

    Optional<Subject> findSubjectByNameAndGrade(String name, EnumGrade grade);
}
