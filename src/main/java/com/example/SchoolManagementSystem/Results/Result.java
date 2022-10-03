package com.example.SchoolManagementSystem.Results;


import com.example.SchoolManagementSystem.Schools.School;
import com.example.SchoolManagementSystem.Student.Student;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Integer term;
}
