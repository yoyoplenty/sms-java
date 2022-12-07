package com.example.SchoolManagementSystem.Users.Dto;

import com.example.SchoolManagementSystem.Address.Dto.NewAddressDto;
import com.example.SchoolManagementSystem.Utils.Dto.UserDto;
import lombok.Data;

import java.util.UUID;

@Data
public class NewUserDto extends UserDto {
    private UUID schoolId;

    private NewAddressDto address;
}
