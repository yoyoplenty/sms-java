package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Role.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "email")
    private String email;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "user_type")
    private EnumUserType userType;

    @Column(name = "password")
    private String password;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "confirm_token")
    private String confirmToken;

    @Column(name = "reset_token")
    private String resetToken;

    private String username;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();
}