package org.example.e_commerceproject.service;

import jakarta.transaction.Transactional;
import org.example.e_commerceproject.model.*;
import org.example.e_commerceproject.repository.CartRepository;
import org.example.e_commerceproject.repository.OrderRepository;
import org.example.e_commerceproject.repository.PaymentRepository;
import org.example.e_commerceproject.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ShippingRepository shippingRepository;
    @Transactional
    public Order checkout(User user, Payment payment, Shipping shipping) {
        // Save the payment information
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);

        // Save the shipping information
        shipping.setShippingDate(LocalDateTime.now());
        shippingRepository.save(shipping);

        // Create the order
        Order order = new Order();
        order.setUser(user);
        order.setPayment(payment);
        order.setShipping(shipping);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Processing");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        // Save the order
        return orderRepository.save(order);
    }
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersByUser(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
