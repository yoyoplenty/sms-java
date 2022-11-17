package com.example.SchoolManagementSystem.Role.Dto;

import com.example.SchoolManagementSystem.Enum.EnumRole;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class CreateRoleDto {
    @Enumerated(EnumType.STRING)
    public EnumRole name;
}

