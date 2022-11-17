package com.example.SchoolManagementSystem.Event;

import com.example.SchoolManagementSystem.School.School;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "event")
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
