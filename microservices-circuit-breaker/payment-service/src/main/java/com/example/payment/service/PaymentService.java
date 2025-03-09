package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(String orderId, double amount) {
        boolean paymentSuccess = new Random().nextBoolean();
        String status = paymentSuccess ? "Success" : "Failed";

        Payment payment = new Payment(orderId, status, amount);
        return paymentRepository.save(payment);
    }
}
