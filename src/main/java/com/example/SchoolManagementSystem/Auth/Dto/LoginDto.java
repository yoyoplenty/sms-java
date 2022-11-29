package com.example.SchoolManagementSystem.Auth.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    private String email;

    private String studentId;

    @Size(min = 5, max = 50, message = "last name should have 3 -15 characters")
    @NotBlank(message = "password cannot be empty")
    private String password;
}
