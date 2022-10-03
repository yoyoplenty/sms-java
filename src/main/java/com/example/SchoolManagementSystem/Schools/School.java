package com.example.SchoolManagementSystem.Schools;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@Table(name = "schools", uniqueConstraints = {@UniqueConstraint(columnNames = {"schoolEmail"})})
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "schoolName")
    @NotBlank(message = "school name cannot be empty")
    private String schoolName;

    @Column(name = "schoolAddress")
    @NotBlank(message = "school address cannot be left empty")
    private String schoolAddress;

    @Column(name = "schoolEmail")
    @Email(message = "please provide a valid email address")
    @NotBlank(message = "school email cannot be empty")
    private String schoolEmail;

    @Column(name = "contactPersonName")
    @NotBlank(message = "contact person's name cannot be empty")
    private String contactPersonName;

    @Column(name = "contactPersonEmail")
    @NotBlank(message = "contact person's email cannot be empty")
    private String contactPersonEmail;

    @Column(name = "contactPersonPhone")
    @NotBlank(message = "contact person's phone cannot be empty")
    private String contactPersonPhone;

    @Column(name = "isActive")
    private Boolean isActive = true;

}
