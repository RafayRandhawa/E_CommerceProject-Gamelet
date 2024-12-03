package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.model.*;
import org.example.e_commerceproject.service.OrderService;
import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/process")
    public Order checkout(@RequestBody Order order) {
        return orderService.checkout((User)sessionService.getAttribute("user"), order.getPayment(), order.getShipping());
    }
}