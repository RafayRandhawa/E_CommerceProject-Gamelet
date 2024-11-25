package org.example.e_commerceproject.repository;

import org.example.e_commerceproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find Orders by User
    //List<Order> findByUserId(Long userId);

    // Find Orders by Status
    List<Order> findByStatus(String status);

    // Find Orders by Date Range
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find Orders by User and Status
    //List<Order> findByUserIdAndStatus(Long userId, String status);

    // Count Orders by Status
    Long countByStatus(String status);

    // Find Orders by User and Date Range
    //List<Order> findByUserIdAndOrderDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
