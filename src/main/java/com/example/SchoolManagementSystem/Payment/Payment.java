package com.example.SchoolManagementSystem.Payment;

import com.example.SchoolManagementSystem.Events.Event;
import com.example.SchoolManagementSystem.Schools.School;
import com.example.SchoolManagementSystem.Student.Student;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payments")
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
