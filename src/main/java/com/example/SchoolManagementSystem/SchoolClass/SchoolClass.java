package com.example.SchoolManagementSystem.SchoolClass;

import com.example.SchoolManagementSystem.Schools.School;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "class")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String grade;

    private String department;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    //one to one relationship with teacher
}
