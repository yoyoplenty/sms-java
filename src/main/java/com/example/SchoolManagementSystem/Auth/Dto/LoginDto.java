package com.example.SchoolManagementSystem.Auth.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {
    private String email;

    private String studentId;

    @NotEmpty
    private String password;
}
