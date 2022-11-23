package com.example.SchoolManagementSystem.Student.Dto;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Data
public class NewStudentDto {

    private String studentId;

    private UUID schoolId;

    @NotBlank(message = "firstname cannot be empty")
    private String firstName;

    @NotBlank(message = "lastname cannot be empty")
    private String lastName;

    private String middleName;

    private String phoneNumber;

    private EnumUserType userType;

    @NotBlank(message = "password cannot be empty")
    private String password;

    private UUID roleId;

    private Boolean locked;

    private Boolean enabled;

    private String confirmToken;

    private String accessToken;

    private String username;

}
