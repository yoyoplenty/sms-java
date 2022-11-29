package com.example.SchoolManagementSystem.Subject.Dto;

import com.example.SchoolManagementSystem.Utils.Dto.SubjectDetailsDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NewSubjectDto extends SubjectDetailsDto {
    @Size(min = 4, max = 4, message = "subject code should have 4 characters")
    @NotBlank(message = "subject code cannot be empty")
    private String code;
}
