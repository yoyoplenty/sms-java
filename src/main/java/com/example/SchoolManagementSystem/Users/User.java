package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Roles.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "confirmToken")
    private String confirmToken;

    @Column(name = "accessToken")
    private String accessToken;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id")
//    private Role role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
