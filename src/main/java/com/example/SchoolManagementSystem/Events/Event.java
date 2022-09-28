package com.example.SchoolManagementSystem.Events;

import com.example.SchoolManagementSystem.Schools.School;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //This should be an enum[]
    @Column(name = "eventType")
    private String eventType;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "amount")
    private Long amount;

//    @Column(name = "eventTime")
//    @Temporal(value = TemporalType.TIMESTAMP)
//    private Date eventTime;
//
//    @Column(name = "eventDate")
//    @Temporal(value = TemporalType.TIMESTAMP)
//    private Date eventDate;

    @Column(name = "isActive")
    private Boolean isActive;
}
