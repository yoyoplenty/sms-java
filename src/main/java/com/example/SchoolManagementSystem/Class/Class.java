package com.example.SchoolManagementSystem.Class;

import com.example.SchoolManagementSystem.Enum.EnumGrade;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.Teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private EnumGrade grade;

    @Column(name = "department")
    private String department;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
