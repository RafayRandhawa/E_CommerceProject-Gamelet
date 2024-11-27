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
            name = "product_id",
            foreignKey = @ForeignKey(
                    name = "fk_product_review",
                    foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES Product(product_id) DEFERRABLE INITIALLY DEFERRED"
            )
    ) // Matches the primary key in the Product table
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
