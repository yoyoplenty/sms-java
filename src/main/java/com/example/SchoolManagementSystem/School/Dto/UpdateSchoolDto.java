package com.example.SchoolManagementSystem.School.Dto;

import com.example.SchoolManagementSystem.Address.Dto.NewAddressDto;
import lombok.Data;

@Data
public class UpdateSchoolDto {
    private String name;
    private String email;
    private NewAddressDto address;
    private Boolean locked;
}
