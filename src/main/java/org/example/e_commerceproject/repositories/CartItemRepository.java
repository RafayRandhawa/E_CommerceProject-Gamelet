package org.example.e_commerceproject.repositories;

import org.example.e_commerceproject.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Find CartItem by Cart ID and Product ID
    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    // Find all CartItems by Cart ID
    List<CartItem> findByCartId(Long cartId);

    // Find CartItems by User ID
    @Query("SELECT ci FROM CartItem ci JOIN ci.cart c WHERE c.user.id = :userId")
    List<CartItem> findByUserId(@Param("userId") Long userId);

    // Find all CartItems by Product ID
    List<CartItem> findByProductId(Long productId);

    // Count CartItems by Cart ID
    Long countByCartId(Long cartId);

    // Find CartItems for Active Carts
    @Query("SELECT ci FROM CartItem ci JOIN ci.cart c WHERE c.isActive = true")
    List<CartItem> findItemsForActiveCarts();
}
