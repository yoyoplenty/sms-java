package com.example.SchoolManagementSystem.Users.Dto;

import com.example.SchoolManagementSystem.Users.Annotation.EmailPresent;
import com.example.SchoolManagementSystem.Utils.Dto.UserDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
public class NewUserDto extends UserDto {
    @NotBlank(message = "email cannot be empty")
    @EmailPresent(message = "email: ${validatedValue} is already present")
    private String email;

    @Size(min = 11, max = 11, message = "phone number should have 11 characters")
    @NotBlank(message = "phone number cannot be empty")
    private String phoneNumber;

    @NotEmpty(message = "role cannot be empty")
    @NotNull
    private List<UUID> roleId;
}
