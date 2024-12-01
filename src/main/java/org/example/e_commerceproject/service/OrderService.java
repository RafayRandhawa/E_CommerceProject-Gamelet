package org.example.e_commerceproject.service;

import org.example.e_commerceproject.model.Order;
import org.example.e_commerceproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getOrdersByUser(int userId) {
        return orderRepository.findByUserId(userId);
    }
}
