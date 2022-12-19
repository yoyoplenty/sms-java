package com.example.SchoolManagementSystem.PaymentDetails.Dto;

import com.example.SchoolManagementSystem.Enum.EnumTerm;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class PaymentDetailsDto {

    @NotNull(message = "payment name cannot be empty")
    private String name;

    private UUID paymentId;

    private UUID studentId;

    private UUID staffId;

    private EnumTerm term;

    private UUID eventId;
}
