package com.example.SchoolManagementSystem.Utils.Dto;


import com.example.SchoolManagementSystem.Enum.EnumGrade;
import com.example.SchoolManagementSystem.Utils.Annotations.GradeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SubjectDetailsDto {
    @Size(min = 3, max = 25, message = "name should have 3 -25 characters")
    @NotBlank(message = "subject name cannot be empty")
    private String name;

    @NotNull(message = "grade should not be null")
    @GradeEnum(anyOf = {EnumGrade.JUNIOR, EnumGrade.SENIOR})
    private EnumGrade grade;
}
