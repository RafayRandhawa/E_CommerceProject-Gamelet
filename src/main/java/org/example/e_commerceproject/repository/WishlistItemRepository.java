package org.example.e_commerceproject.repository;

import org.example.e_commerceproject.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

    // Find WishlistItem by Wishlist ID
    List<WishlistItem> findByWishlistItemId(Long wishlistId);

    // Find WishlistItem by Product ID
}
