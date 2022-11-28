package com.example.SchoolManagementSystem.Utils.Dto;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Utils.Annotations.UserTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    @Size(min = 3, max = 15, message = "first name should have 3 -15 characters")
    @NotBlank(message = "firstname cannot be empty")
    private String firstName;

    @Size(min = 3, max = 15, message = "last name should have 3 -15 characters")
    @NotBlank(message = "lastname cannot be empty")
    private String lastName;

    @Size(min = 3, max = 15, message = "last name should have 3 -15 characters")
    private String middleName;

    @NotNull(message = "user type cannot be empty")
    @UserTypeEnum(message = "invalid user type", anyOf = {EnumUserType.USER, EnumUserType.STUDENT,
            EnumUserType.ADMIN, EnumUserType.TEACHER, EnumUserType.SCHOOL_ADMIN})
    private EnumUserType userType;

    @Size(min = 5, max = 50, message = "last name should have 3 -15 characters")
    @NotBlank(message = "password cannot be empty")
    private String password;

    private Boolean locked;

    private Boolean enabled;

    private String confirmToken;

    private String accessToken;

    private String username;
}
