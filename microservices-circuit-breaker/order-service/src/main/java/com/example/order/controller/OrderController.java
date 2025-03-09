package com.example.order.controller;

import com.example.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;


@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
        public String placeOrder(@RequestParam String productId) {
        return orderService.placeOrder(productId); }

    @GetMapping("/")
        public String home() {
            return "index";
        }

    @GetMapping("/placeOrder")
        public String placeOrder(@RequestParam String productId, Model model) {
        String response = orderService.placeOrder(productId);
        model.addAttribute("message", response);
        return "index";
        }

}
