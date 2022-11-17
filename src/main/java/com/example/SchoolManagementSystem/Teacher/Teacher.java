package com.example.SchoolManagementSystem.Teacher;


import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.Subject.Subject;
import com.example.SchoolManagementSystem.Users.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "firstname")
    @NotBlank(message = "firstName cannot be empty")
    private String firstName;

    @Column(name = "lastname")
    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @Column(name = "email")
    @Email(message = "please provide a valid email address")
    @NotBlank(message = "email cannot be empty")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subject_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new ArrayList<>();
}
