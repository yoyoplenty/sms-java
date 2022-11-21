package com.example.SchoolManagementSystem.Subject;

import com.example.SchoolManagementSystem.Enum.EnumGrade;
import com.example.SchoolManagementSystem.Teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "grade")
    private EnumGrade grade;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Teacher> teachers;
}
