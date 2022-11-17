package com.example.SchoolManagementSystem.Payment;

import com.example.SchoolManagementSystem.Event.Event;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.Student.Student;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private String paymentType;

    private Integer term;

    private Long amount;

    private String payRef;

    private String status;
}
