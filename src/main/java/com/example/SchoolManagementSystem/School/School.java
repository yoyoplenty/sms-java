package com.example.SchoolManagementSystem.School;

import com.example.SchoolManagementSystem.Address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class School {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    @Email(message = "please provide a valid email address")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "school_id")
    private List<Address> address;

    @Column(name = "locked", nullable = false)
    private Boolean locked = false;

    //TODO all school events
    //TODO all school payments
}
