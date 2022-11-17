package com.example.SchoolManagementSystem.Utils.Email;

import com.example.SchoolManagementSystem.Utils.Email.Dto.MailData;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.OkResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class EmailService {

    @Value("${API_KEY}")
    private String API_KEY;

    @Value("${DOMAIN}")
    private String DOMAIN;

    @Async
    public void sendEmail(MailData mailData) throws UnirestException {
        com.mashape.unirest.http.HttpResponse<String> request = Unirest.post("https://api.mailgun.net/v3/" + DOMAIN + "/messages")
                .basicAuth("api", API_KEY)
                .queryString("from", " HAIM SMS USER@sandbox0b0510b36af54f0c9aea7f80a418c0a2.mailgun.org")
                .queryString("to", mailData.getReceiver())
                .queryString("subject", mailData.getSubject())
                .queryString("text", mailData.getBody())
                .asString();

        System.out.println(request.getBody());

        if (request.getStatus() == 200) {
            OkResponse mailResponse = new OkResponse("email successfully sent", 200, null);

            CompletableFuture.completedFuture(mailResponse);
        } else throw new IllegalStateException("unable to send email");
    }
}
