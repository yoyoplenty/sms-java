package com.example.SchoolManagementSystem.PaymentDetails;

import com.example.SchoolManagementSystem.Enum.EnumTerm;
import com.example.SchoolManagementSystem.Event.Event;
import com.example.SchoolManagementSystem.Payment.Payment;
import com.example.SchoolManagementSystem.Student.Student;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

public class PaymentDetails {
    @Id
    @GeneratedValue
    private final UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "payment_id")
    private Payment paymentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Student staff;

    @Column(name = "term")
    private EnumTerm term;

    @Column(name = "amount_paid")
    private Long amountPaid;

    @Column(name = "date")
    private Date date;
}
