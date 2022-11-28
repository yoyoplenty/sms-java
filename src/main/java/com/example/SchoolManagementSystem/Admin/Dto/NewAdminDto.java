package com.example.SchoolManagementSystem.Admin.Dto;

import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import lombok.Data;

import java.util.UUID;

@Data
public class NewAdminDto extends NewUserDto {
    private String middleName;

    private String staffId;

    private UUID schoolId;
}
