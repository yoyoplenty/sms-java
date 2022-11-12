package com.example.SchoolManagementSystem.Users.Dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class NewUserDto {
    @NotBlank(message = "password cannot be empty")
    private String firstName;

    @NotBlank(message = "password cannot be empty")
    private String lastName;

    @NotBlank(message = "password cannot be empty")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String phoneNumber;

    @NotBlank(message = "password cannot be empty")
    private String password;

    private Boolean locked;

    private Boolean enabled;

    private String confirmToken;

    private String accessToken;

    private String username;

    @NotEmpty
    private List<UUID> roleId = new ArrayList<>();
}
