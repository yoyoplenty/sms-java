package com.example.SchoolManagementSystem.Student.Dto;

import com.example.SchoolManagementSystem.Utils.Dto.UserDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;


@Data
public class NewStudentDto extends UserDto {
    private String studentId;

    @NotNull(message = "school id cannot be empty")
    private UUID schoolId;

    @Size(min = 11, max = 11, message = "phone number should have 11 characters")
    private String phoneNumber;

    @NotNull(message = "role cannot be empty")
    private UUID roleId;
}
