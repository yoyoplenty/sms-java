package com.example.SchoolManagementSystem.Auth.Dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
public class LoginDto {

    @NonNull
    @NotEmpty
    private String email;

    @NonNull
    @NotEmpty
    private String password;
}
