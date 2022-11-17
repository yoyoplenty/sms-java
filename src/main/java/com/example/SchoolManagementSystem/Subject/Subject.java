package com.example.SchoolManagementSystem.Subject;

import com.example.SchoolManagementSystem.Teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    @NotBlank(message = "firstName cannot be empty")
    private String name;

    @Column(name = "code")
    @NotBlank(message = "lastName cannot be empty")
    private String code;

    private String grade;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teachers = new ArrayList<>();
}
