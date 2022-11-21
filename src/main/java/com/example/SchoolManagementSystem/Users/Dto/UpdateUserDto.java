package com.example.SchoolManagementSystem.Users.Dto;

import lombok.Data;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UpdateUserDto {
    private String firstName;

    private String lastName;

    @Email(message = "invalid email")
    private String email;

    private String phoneNumber;

    private String password;

    private final List<UUID> roleId = new ArrayList<>();

    private Boolean locked;

    private Boolean enabled;

    private String confirmToken;

    private String accessToken;

    private String username;
}
