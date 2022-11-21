package com.example.SchoolManagementSystem.Address;

import com.example.SchoolManagementSystem.School.School;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "street")
    private String street;

    @Column(name = "lga")
    private String lga;

    @Column(name = "state")
    private String state;

    @Column(name = "contact_person_name")
    private String contactPersonName;

    @Column(name = "contact_person_email")
    private String contactPersonEmail;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "school_id")
    private School school;
}
