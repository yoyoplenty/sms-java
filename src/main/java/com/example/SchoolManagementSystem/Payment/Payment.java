package com.example.SchoolManagementSystem.Payment;

import com.example.SchoolManagementSystem.Enum.EnumPaymentType;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.Users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "payment_type")
    private EnumPaymentType paymentType;

    @Column(name = "amount")
    private Long amount;

    private String payRef;

    private String status;
}
