package org.example.e_commerceproject.service;

import org.example.e_commerceproject.model.*;
import org.example.e_commerceproject.repository.OrderRepository;
import org.example.e_commerceproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.e_commerceproject.repository.OrderItemsRepository;
import org.example.e_commerceproject.model.OrderItems;

import java.time.LocalDate;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemRepository; // Used to retrieve the associated product

    public void saveReview(Long orderId, User user, int rating, String reviewText) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        // Removed product validation logic

        Review review = new Review();
        review.setOrder(order);
        review.setUser(user);
        review.setRating(rating);
        review.setReviewText(reviewText);
        review.setReviewDate(LocalDate.now());
        review.setApproved(false); // Initially unapproved

        reviewRepository.save(review);
    }
}
