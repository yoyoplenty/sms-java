package com.example.SchoolManagementSystem.Utils.Email;

import com.example.SchoolManagementSystem.Enum.EnumEmailContent;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Utils.Email.Dto.MailData;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.OkResponse;
import com.example.SchoolManagementSystem.Utils.Helpers.TokenService;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Service
public class EmailService {

    @Value("${API_KEY}")
    private String API_KEY;

    @Value("${DOMAIN}")
    private String DOMAIN;

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailContent emailContent;


    @Async
    public void sendEmail(MailData mailData) throws UnirestException {
        com.mashape.unirest.http.HttpResponse<String> request = Unirest.post("https://api.mailgun.net/v3/" + DOMAIN + "/messages")
                .basicAuth("api", API_KEY)
                .queryString("from", " HAIM SCHOOL MANAGEMENT SYSTEM USER@sandbox0b0510b36af54f0c9aea7f80a418c0a2.mailgun.org")
                .queryString("to", mailData.getReceiver())
                .queryString("subject", mailData.getSubject())
                .queryString("text", mailData.getBody())
                .asString();

        logger.info(request.getBody());

        if (request.getStatus() == 200) {
            OkResponse mailResponse = new OkResponse("email successfully sent", 200, null);

            CompletableFuture.completedFuture(mailResponse);
        } else throw new IllegalStateException("user saved, unable to send email");
    }

    public void sendEmailToUser(User user, EnumEmailContent RegistrantEmail) throws UnirestException {

        Map<String, String> content = generateContent(RegistrantEmail, user);

        MailData newMailData = MailData.builder()
                .receiver(user.getEmail())
                .body(content.get("body"))
                .subject(content.get("subject"))
                .build();

        sendEmail(newMailData);
    }

    public Map<String, String> generateContent(EnumEmailContent mailContent, User user) {
        String token = tokenService.generateToken(user.getConfirmToken());

        switch (mailContent) {
            case RegistrantEmail -> {
                return emailContent.RegistrationMail(user, token);
            }
            case ForgetPasswordEmail -> System.out.println("Tuesday");
        }
        return null;
    }

}
