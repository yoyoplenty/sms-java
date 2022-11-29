package com.example.SchoolManagementSystem.Utils.Email;

import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Utils.Helpers.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailContent {

    @Autowired
    TokenService tokenService;


    public Map<String, Object> RegistrationMail(User user) {
        String token = tokenService.generateToken(user.getConfirmToken());

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Welcome to HAIM School Management System. ").append(user.getEmail());
//        emailBody.append("Welcome to HAIM School Management System. ").append(userDetails.getFirstName()).append(" ").append(userDetails.getLastName());
        emailBody.append(" You are welcome, please click on the link below to activate your account ");
        emailBody.append(" localhost:5050/auth/confirm_email/").append(token);

        Map<String, Object> mailContent = new HashMap<>();
        mailContent.put("subject", "Registration Confirmed");
        mailContent.put("body", emailBody);

        return mailContent;
    }

    public Map<String, Object> ForgetPasswordMail(User user) {
        String token = tokenService.generateToken(user.getResetToken());

        StringBuilder emailBody = new StringBuilder();
        emailBody.append(" You are welcome, please click on the link below to activate your account ");
        emailBody.append("localhost:5050/auth/reset_password/").append(token);

        Map<String, Object> mailContent = new HashMap<>();
        mailContent.put("subject", "Forget Password");
        mailContent.put("body", emailBody);

        return mailContent;
    }
}
