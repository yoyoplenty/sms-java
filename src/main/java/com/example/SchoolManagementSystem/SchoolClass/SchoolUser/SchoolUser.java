package com.example.SchoolManagementSystem.SchoolClass.SchoolUser;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "schoolUsers", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class SchoolUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstName")
    @NotBlank(message = "firstName cannot be empty")
    private String firstName;

    @Column(name = "lastName")
    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @Column(name = "email")
    @Email(message = "please provide a valid email address")
    @NotBlank(message = "email cannot be empty")
    private String email;

//    @Column(name = "staffId")
//    @NotBlank(message = "staff id cannot be empty")
//    private String staffId;

    @Column(name = "phoneNumber")
    @Email(message = "please provide a valid phone number")
    @NotBlank(message = "phone number cannot be empty")
    private String phoneNumber;


    @Column(name = "role")
    private String role;

    private String schoolId;


    private String userId;


    private Boolean isActive;
}
