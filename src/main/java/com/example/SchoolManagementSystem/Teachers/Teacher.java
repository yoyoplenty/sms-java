package com.example.SchoolManagementSystem.Teachers;


import com.example.SchoolManagementSystem.Schools.School;
import com.example.SchoolManagementSystem.Subjects.Subject;
import com.example.SchoolManagementSystem.Users.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Data
@Entity
@Table(name = "teachers")
public class Teacher {
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

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Collection<Subject> subjects;
}
