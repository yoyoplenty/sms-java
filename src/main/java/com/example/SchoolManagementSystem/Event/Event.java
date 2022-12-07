package com.example.SchoolManagementSystem.Event;

import com.example.SchoolManagementSystem.Enum.EnumEventType;
import com.example.SchoolManagementSystem.School.School;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "event_type")
    private EnumEventType eventType;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "event_time")
    @Temporal(value = TemporalType.TIME)
    private Date eventTime;

    @Column(name = "event_date")
    @Temporal(value = TemporalType.DATE)
    private Date eventDate;

    @Column(name = "isActive")
    private Boolean isActive;
}
