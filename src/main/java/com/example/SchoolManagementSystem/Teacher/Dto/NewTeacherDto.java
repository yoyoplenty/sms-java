package com.example.SchoolManagementSystem.Teacher.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
public class NewTeacherDto {

    private String middleName;

    private String staffId;

    private UUID schoolId;

    private UUID userId;

    @NotEmpty(message = "subject id's must not be empty")
    private List<UUID> subjectId;
}
