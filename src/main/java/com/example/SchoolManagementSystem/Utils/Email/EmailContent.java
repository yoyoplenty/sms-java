package com.example.SchoolManagementSystem.Utils.Email;

import com.example.SchoolManagementSystem.Users.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailContent {

    public Map<String, String> RegistrationMail(User user, String token) {
        String name = user.getFirstName();
        String emailBody = String
                .format("Welcome to HAIM School Management System"
                        + name + "You are welcome, please click on the link below to activate your account " + user.getLastName() +
                        " localhost:5050/auth/confirm_email/" + token);

        Map<String, String> mailContent = new HashMap<>();
        mailContent.put("subject", "Registration Confirmed");
        mailContent.put("body", emailBody);

        return mailContent;
    }

}
