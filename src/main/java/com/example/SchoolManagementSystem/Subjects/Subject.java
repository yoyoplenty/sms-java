package com.example.SchoolManagementSystem.Subjects;

import com.example.SchoolManagementSystem.Teachers.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "firstName cannot be empty")
    private String name;

    @Column(name = "code")
    @NotBlank(message = "lastName cannot be empty")
    private String code;

    private String grade;
    
    @ManyToMany(mappedBy = "subjects")
    private Collection<Teacher> teachers;
}
