package com.example.SchoolManagementSystem.Student;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Role.Roles;
import com.example.SchoolManagementSystem.School.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "student_id")
    private String studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_type")
    private EnumUserType userType = EnumUserType.Student;

    @Column(name = "password")
    private String password;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "enabled")
    private Boolean enabled;

    private String username;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Roles roles;

//    private String password;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User user;
}
