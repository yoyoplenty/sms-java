package com.example.SchoolManagementSystem.Utils.Flutterwave;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flutterwave.rave.java.entry.transValidation;
import com.flutterwave.rave.java.payload.transverifyPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class TransactionService {

    @Value("${FLUTTERWAVE_SECRET_KEY}")
    private String SECRET_KEY;


    //This Is Using the flutter-wave api to verify payment made to your application
    public Map<String, Object> verifyPayment(String transactionRef) {
        System.out.println("i am here with the reference" + transactionRef);

        String url = "https://api.flutterwave.com/v3/transactions/" + transactionRef + "/verify";
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(SECRET_KEY);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<Map<String, Object>> response = rest.exchange(url, HttpMethod.GET, httpEntity, typeRef);
        return response.getBody();
    }

    //This Is Using the JAVA package from flutter-wave
    public Object verifyTransaction(String transactionRef) throws JsonProcessingException {

        transValidation transValidation = new transValidation();
        transverifyPayload transverifyPayload = new transverifyPayload();
        transverifyPayload.setSECKEY(SECRET_KEY);
        transverifyPayload.setTxref(transactionRef);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = transValidation.bvnvalidate(transverifyPayload);

        return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
    }
}
