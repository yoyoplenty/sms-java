package com.example.SchoolManagementSystem.Event.Dto;

import com.example.SchoolManagementSystem.Enum.EnumEventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
public class NewEventDto {
    @NotBlank(message = "event name cannot be empty")
    private String name;

    private EnumEventType eventType;

    @NotNull(message = "school id cannot be empty")
    private UUID schoolId;

    @Range(min = 1000, max = 1000000, message = "amount " +
            "must not be less than 1000 and greater than 1,000,000")
    private Long amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "Please provide a valid date in the format \"dd-MM-yyyy HH:mm\" ")
    private Date date;

    private Boolean isActive;
}
