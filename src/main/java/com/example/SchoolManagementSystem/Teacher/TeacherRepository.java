package com.example.SchoolManagementSystem.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Optional<Teacher> findTeacherById(UUID id);

    Optional<Teacher> findTeacherByStaffId(String staffId);

    Optional<Teacher> findTeacherByUserId(UUID userId);

    @Query("select t from Teacher t join t.subjects s where s.id = :subjectId and t.id = :teacherId")
    Teacher findSubjectInTeacher(@Param("subjectId") UUID subjectId, @Param("teacherId") UUID teacherId);

}
