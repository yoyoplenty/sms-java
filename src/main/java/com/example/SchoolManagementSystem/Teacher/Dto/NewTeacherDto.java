package com.example.SchoolManagementSystem.Teacher.Dto;

import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
public class NewTeacherDto extends NewUserDto {

    private String middleName;

    private String staffId;

    private UUID schoolId;

    @NotEmpty(message = "subject id's must not be empty")
    private List<UUID> subjectId;
}
