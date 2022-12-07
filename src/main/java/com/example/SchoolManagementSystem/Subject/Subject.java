package com.example.SchoolManagementSystem.Subject;

import com.example.SchoolManagementSystem.Enum.EnumGrade;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.Teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "grade")
    private EnumGrade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects", cascade =
            {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Teacher> teachers;
}
