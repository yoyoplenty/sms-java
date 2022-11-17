package com.example.SchoolManagementSystem.Auth.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
