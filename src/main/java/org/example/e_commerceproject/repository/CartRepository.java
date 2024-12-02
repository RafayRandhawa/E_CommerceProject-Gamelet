package org.example.e_commerceproject.repository;
import org.example.e_commerceproject.model.Cart;
import org.example.e_commerceproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Find cart by user ID
    //Cart findByUserId(Long userId);

    // Find active carts by user ID
    //List<Cart> findByUserIdAndIsActive(Long userId, Boolean isActive);

    // Find all active carts
    List<Cart> findByIsActive(Boolean isActive);

    // Find cart by ID and include cart items
    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.cartId = :cartId")
    Cart findByIdWithItems(@Param("cartId") Long cartId);

    // Count carts by user ID
    //Long countByUserId(Long userId);

    // Find all carts and sort by createdAt descending
    List<Cart> findAllByOrderByCreatedAtDesc();

    // Find active carts within a specific date range
    List<Cart> findByIsActiveAndCreatedAtBetween(Boolean isActive, LocalDateTime startDate, LocalDateTime endDate);

    // Find carts by user ID within a specific date range
    //List<Cart> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    Optional<Cart> findByUserAndIsActive(User user, boolean isActive);
}
