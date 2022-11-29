package com.example.SchoolManagementSystem.Admin;

import com.example.SchoolManagementSystem.Address.Address;
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
@Table(name = "admin", uniqueConstraints = {@UniqueConstraint(columnNames = {"staff_id"})})
public class Admin {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
