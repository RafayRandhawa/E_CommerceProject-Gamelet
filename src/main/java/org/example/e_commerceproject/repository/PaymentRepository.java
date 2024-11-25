package org.example.e_commerceproject.repository;

import org.example.e_commerceproject.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Find Payments by Payment Type
    List<Payment> findByPaymentType(String paymentType);

    // Find Payments by Date Range
    List<Payment> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find Payments by Amount Greater Than
    List<Payment> findByAmountGreaterThan(Double amount);

    // Find Payments by Amount Less Than
    List<Payment> findByAmountLessThan(Double amount);

    // Find Payments by Payment Type and Amount Greater Than
    List<Payment> findByPaymentTypeAndAmountGreaterThan(String paymentType, Double amount);

    // Count Payments by Payment Type
    Long countByPaymentType(String paymentType);
}
