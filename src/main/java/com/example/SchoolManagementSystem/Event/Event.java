package com.example.SchoolManagementSystem.Event;

import com.example.SchoolManagementSystem.Enum.EnumEventType;
import com.example.SchoolManagementSystem.School.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "event_type")
    private EnumEventType eventType;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "event_date")
    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column(name = "isActive")
    private Boolean isActive;
}

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Column(name = "event_time")
//    @Temporal(value = TemporalType.TIME)
//    private Date eventTime;
