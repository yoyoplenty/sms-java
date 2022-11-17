package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Role.Roles;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "firstname")
    @NonNull
    private String firstName;

    @Column(name = "lastname")
    @NonNull
    private String lastName;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "phone_number")
    @NonNull
    private String phoneNumber;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "locked", nullable = false)
    private Boolean locked = false;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;
    
    @Column(name = "confirm_token")
    private String confirmToken;

    @Column(name = "access_token")
    private String accessToken;

    private String username;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();
}
