package org.example.e_commerceproject.repositories;

import org.example.e_commerceproject.models.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

    // Find WishlistItem by Wishlist ID
    List<WishlistItem> findByWishlistId(Long wishlistId);

    // Find WishlistItem by Product ID
    Optional<WishlistItem> findByProductId(Long productId);

    // Find WishlistItem by Wishlist ID and Product ID
    Optional<WishlistItem> findByWishlistIdAndProductId(Long wishlistId, Long productId);

    // Remove WishlistItem by Wishlist ID and Product ID (Custom Delete Query)
    void deleteByWishlistIdAndProductId(Long wishlistId, Long productId);
}
