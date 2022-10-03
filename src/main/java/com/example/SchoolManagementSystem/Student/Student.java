package com.example.SchoolManagementSystem.Student;

import com.example.SchoolManagementSystem.Schools.School;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstName")
    @NotBlank(message = "firstName cannot be empty")
    private String firstName;

    @Column(name = "middleName")
    @NotBlank(message = "Middle Name cannot be empty")
    private String middleName;

    @Column(name = "lastName")
    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @Column(name = "regNumber")
    private String regNumber;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "password")
    private String password;

    @Column(name = "isActive")
    private Boolean isActive;
}
