package com.example.SchoolManagementSystem.Payment;

import com.example.SchoolManagementSystem.Payment.Dto.NewPaymentDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<Object> createPayment(@RequestBody @Valid NewPaymentDto newPaymentDto) {
        try {

            Object paymentDetails = paymentService.createPayment(newPaymentDto.getTransactionRef());

            return ResponseHandler.generateResponse("Successfully created data", HttpStatus.OK, paymentDetails);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
