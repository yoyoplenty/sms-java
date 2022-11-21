package com.example.SchoolManagementSystem.Subject.Dto;

import com.example.SchoolManagementSystem.Enum.EnumGrade;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewSubjectDto {
    @NotBlank(message = "subject name cannot be empty")
    private String name;

    @NotBlank(message = "subject code cannot be empty")
    private String code;

    private EnumGrade grade;

//    private List<Teacher> teachers;
}
