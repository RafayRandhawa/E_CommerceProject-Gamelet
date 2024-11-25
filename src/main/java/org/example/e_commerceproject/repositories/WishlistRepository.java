package org.example.e_commerceproject.repositories;

import org.example.e_commerceproject.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // Find Wishlist by User
    Optional<Wishlist> findByUserId(Long userId);

    // Check if a User has an Existing Wishlist
    boolean existsByUserId(Long userId);
}
