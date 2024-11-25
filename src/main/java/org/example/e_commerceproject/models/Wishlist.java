package org.example.e_commerceproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_seq")
    @SequenceGenerator(name = "wishlist_seq", sequenceName = "WISHLIST_SEQ", allocationSize = 1)
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(
            name = "id",
            foreignKey = @ForeignKey(
                    name = "fk_user_wishlist",
                    foreignKeyDefinition = "FOREIGN KEY (id) REFERENCES Users(id) DEFERRABLE INITIALLY DEFERRED"
            )
    ) // References the "id" field in the User table
    private User user;

    @OneToMany(mappedBy = "wishlist")
    private List<WishlistItem> wishlistItems;

    // Getters and Setters

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }
}
