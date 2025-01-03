package org.example.e_commerceproject.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @SequenceGenerator(name = "review_seq", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            foreignKey = @ForeignKey(
                    name = "fk_order_review",
                    foreignKeyDefinition = "FOREIGN KEY (order_id) REFERENCES Orders(order_id) DEFERRABLE INITIALLY DEFERRED"
            )
    ) // Matches the primary key in the Product table
    private Order order;

    @ManyToOne
    @JoinColumn(
            name = "id",
            foreignKey = @ForeignKey(
                    name = "fk_user_review",
                    foreignKeyDefinition = "FOREIGN KEY (id) REFERENCES Users(id) DEFERRABLE INITIALLY DEFERRED"
            )
    ) // References the "id" field in the User table
    private User user;

    @Column(nullable = false)
    private Integer rating;

    @Column
    private String reviewText;

    @Column(nullable = false)
    private LocalDate reviewDate;

    @Column
    private Boolean isApproved;

    // Getters and Setters

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
