package com.example.SchoolManagementSystem.Student.Dto;

import com.example.SchoolManagementSystem.Student.Annotation.StudentPresent;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@StudentPresent(message = "student already present")
@Data
public class NewStudentDto extends NewUserDto {
    private String studentId;

    @NotNull(message = "school id cannot be empty")
    private UUID schoolId;
}
