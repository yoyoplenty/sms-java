package com.example.SchoolManagementSystem.Payment;

import com.example.SchoolManagementSystem.Utils.Flutterwave.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    TransactionService transactionService;

    public Object createPayment(String transactionRef) throws JsonProcessingException {
//        return transactionService.verifyTransaction(transactionRef);
        return transactionService.verifyPayment(transactionRef);

//        if (paymentDetails.getStatus() == "successfull") return "hi";
    }

}
