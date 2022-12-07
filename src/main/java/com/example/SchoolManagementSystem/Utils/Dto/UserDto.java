package com.example.SchoolManagementSystem.Utils.Dto;

import com.example.SchoolManagementSystem.Auth.Dto.LoginDto;
import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Utils.Annotations.UserTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto extends LoginDto {
    @Size(min = 3, max = 15, message = "first name should have 3 -15 characters")
    @NotBlank(message = "firstname cannot be empty")
    private String firstName;

    @Size(min = 3, max = 15, message = "last name should have 3 -15 characters")
    @NotBlank(message = "lastname cannot be empty")
    private String lastName;

    @Size(min = 3, max = 15, message = "last name should have 3 -15 characters")
    private String middleName;

    @Size(min = 11, max = 11, message = "phone number should have 11 characters")
    private String phoneNumber;

    @UserTypeEnum(message = "invalid user type", anyOf = {EnumUserType.USER, EnumUserType.STUDENT,
            EnumUserType.SYSTEM_ADMIN, EnumUserType.TEACHER, EnumUserType.SCHOOL_ADMIN})
    private EnumUserType userType;
    
    @NotEmpty(message = "role cannot be empty")
    @NotNull
    private List<UUID> roleId;

    private Boolean locked;

    private Boolean enabled;

    private String confirmToken;

    private String resetToken;
}
