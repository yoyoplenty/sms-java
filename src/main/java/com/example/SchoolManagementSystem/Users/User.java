package com.example.SchoolManagementSystem.Users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

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

    @Column(name = "password")
    private String password;

    @Column(name = "phoneNumber")
    private String phoneNumber;

}
