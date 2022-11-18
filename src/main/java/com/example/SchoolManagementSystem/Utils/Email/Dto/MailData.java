package com.example.SchoolManagementSystem.Utils.Email.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MailData {
    private String firstName;
    private String receiver;
    private Object body;
    private String subject;
    private final String sender = "HAIM SMS";
}
