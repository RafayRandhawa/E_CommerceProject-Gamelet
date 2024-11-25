package org.example.e_commerceproject.repository;

import org.example.e_commerceproject.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // Find Wishlist by User
    Optional<Wishlist> findByWishlistId(Long userId);

//    // Check if a User has an Existing Wishlist
//    boolean existsByUserId(Long userId);
}
