package com.example.SchoolManagementSystem.Teacher.Dto;

import com.example.SchoolManagementSystem.Users.Annotation.EmailPresent;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
public class NewTeacherDto extends NewUserDto {
    @NotBlank(message = "email cannot be empty")
    @EmailPresent(message = "email: ${validatedValue} is already present")
    private String email;

    private String staffId;

    private UUID schoolId;
    
    @NotEmpty(message = "subject id's must not be empty")
    private List<UUID> subjectId;
}
