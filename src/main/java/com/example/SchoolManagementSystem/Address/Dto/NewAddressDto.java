package com.example.SchoolManagementSystem.Address.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NewAddressDto {
    @NotEmpty(message = "school street address must not be empty")
    private String street;

    @NotEmpty(message = "school local government area address must not be empty")
    private String lga;

    @NotEmpty(message = "school state must not be empty")
    private String State;

    @NotEmpty(message = "school contact person name must not be empty")
    private String contactPersonName;

    @NotEmpty(message = "school contact person email must not be empty")
    private String contactPersonEmail;
}
