package com.example.payment.controller;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.util.Random;

@RestController
public class PaymentController {
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/payment")
    public String showPaymentPage() {
        return "index"; // Load index.html from templates
    }
    @GetMapping("/")
    public String processPayment() {
        if (new Random().nextBoolean()) {
            paymentRepository.save(new Payment("Failed"));
            throw new RuntimeException("Payment Service Failure");
        }

        paymentRepository.save(new Payment("Successful"));
        return "Payment Successful";
    }

    @PostMapping("/pay")
    public Payment makePayment(@RequestParam String orderId, @RequestParam double amount) {
        return paymentService.processPayment(orderId, amount);
    }

    // Handle payment submission
    @PostMapping("/processPayment")
    public String processPayment(@RequestParam String orderId,
                                 @RequestParam double amount,
                                 Model model) {
        Payment payment = paymentService.processPayment(orderId, amount);
        model.addAttribute("message", "Payment Status: " + payment.getStatus());
        return "index"; // Redirect to the payment page with status
    }
}
