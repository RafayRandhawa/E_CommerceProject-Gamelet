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

    List<Review> findByRating(Integer rating);

    List<Review> findByRatingGreaterThanEqual(Integer rating);

    // Find Reviews Created After a Specific Date
    List<Review> findByReviewDateAfter(LocalDate date);

    // Find Reviews Created Before a Specific Date
    List<Review> findByReviewDateBefore(LocalDate date);

    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.isApproved = :isApproved WHERE r.reviewId = :reviewId")
    int updateIsApprovedById(Long reviewId, boolean isApproved);
}
