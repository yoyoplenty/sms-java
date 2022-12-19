package com.example.SchoolManagementSystem.Student;

import com.example.SchoolManagementSystem.Address.Address;
import com.example.SchoolManagementSystem.Class.Class;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.Users.User;
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
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id"})})
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
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class clazz;

    @JoinColumn(name = "is_cleared")
    private Boolean isCleared;

    @OneToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
