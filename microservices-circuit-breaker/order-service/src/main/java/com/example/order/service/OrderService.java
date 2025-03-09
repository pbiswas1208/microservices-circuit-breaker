package com.example.order.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public String placeOrder(String productId) {
        String paymentResponse = restTemplate.getForObject("http://localhost:8082/payment", String.class);
        return "Order placed. Payment Status: " + paymentResponse;
    }

    public String paymentFallback(String productId, Throwable t) {
        return "Payment Service is down. Please try later.";
    }
}
