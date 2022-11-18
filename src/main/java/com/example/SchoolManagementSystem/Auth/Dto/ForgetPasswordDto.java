package com.example.SchoolManagementSystem.Auth.Dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class ForgetPasswordDto {
    @Email
    private String email;

    private String password;
}

