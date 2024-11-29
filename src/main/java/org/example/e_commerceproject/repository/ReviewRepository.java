package org.example.e_commerceproject.repository;

import jakarta.transaction.Transactional;
import org.example.e_commerceproject.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find Reviews by Product ID
    List<Review> findByProduct_ProductId(Long productId);

    // Find Reviews by User ID
    //List<Review> findByUser_Id(Long userId);

    // Find Reviews by Rating
    List<Review> findByRating(Integer rating);

    // Find Approved Reviews for a Product
    List<Review> findByProduct_ProductIdAndIsApprovedTrue(Long productId);

    // Find Reviews that are Pending Approval for a Product
    List<Review> findByProduct_ProductIdAndIsApprovedFalse(Long productId);

    // Find Reviews by Rating Greater Than or Equal to a Given Value
    List<Review> findByRatingGreaterThanEqual(Integer rating);

    // Find Reviews Created After a Specific Date
    List<Review> findByReviewDateAfter(LocalDate date);

    // Find Reviews Created Before a Specific Date
    List<Review> findByReviewDateBefore(LocalDate date);

    // Find a Single Review by Product ID and User ID
    //Optional<Review> findByProduct_ProductIdAndUser_Id(Long productId, Long userId);
    // Custom query to update the isApproved column for a review by its ID
    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.isApproved = :isApproved WHERE r.id = :reviewId")
    int updateIsApprovedById(Long reviewId, boolean isApproved);
}
