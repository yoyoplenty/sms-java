package com.example.SchoolManagementSystem.School.Dto;

import com.example.SchoolManagementSystem.Address.Dto.NewAddressDto;
import com.example.SchoolManagementSystem.School.Annotation.SchoolEmailPresent;
import com.example.SchoolManagementSystem.School.Annotation.SchoolNamePresent;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class NewSchoolDto {
    @SchoolNamePresent
    @NotEmpty(message = "school name must not be empty")
    private String name;

    @SchoolEmailPresent
    @NotEmpty(message = "school email must not be empty")
    private String email;

    @NotEmpty(message = "school address must not be empty and must be an array")
    private List<NewAddressDto> address;

    private Boolean locked = false;
}
