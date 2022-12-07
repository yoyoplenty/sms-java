package com.example.SchoolManagementSystem.Class.Dto;

import com.example.SchoolManagementSystem.Utils.Dto.SubjectDetailsDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class NewClassDto extends SubjectDetailsDto {

    @Size(min = 3, max = 20, message = "department should have 3 - 20 characters")
    @NotBlank(message = "department cannot be empty")
    private String department;

    private UUID teacherId;
}
