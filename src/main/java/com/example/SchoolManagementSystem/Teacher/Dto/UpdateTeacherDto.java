package com.example.SchoolManagementSystem.Teacher.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpdateTeacherDto {
    private String middleName;

    private String staffId;

    private UUID schoolId;

    private List<UUID> subjectId;
}
