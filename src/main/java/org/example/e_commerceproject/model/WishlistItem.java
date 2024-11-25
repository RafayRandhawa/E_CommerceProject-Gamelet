package org.example.e_commerceproject.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_item_seq")
    @SequenceGenerator(name = "wishlist_item_seq", sequenceName = "WISHLIST_ITEM_SEQ", allocationSize = 1)
    private Long wishlistItemId;

    @ManyToOne
    @JoinColumn(
            name = "wishlist_id",
            foreignKey = @ForeignKey(
                    name = "fk_wishlist_item_wishlist",
                    foreignKeyDefinition = "FOREIGN KEY (wishlist_id) REFERENCES Wishlist(wishlist_Id) DEFERRABLE INITIALLY DEFERRED"
            )
    ) // Matches the primary key in the Wishlist table
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(
                    name = "fk_wishlist_item_product",
                    foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES Product(product_Id) DEFERRABLE INITIALLY DEFERRED"
            )
    ) // Matches the primary key in the Product table
    private Product product;

    private LocalDateTime addedAt;

    // Getters and Setters

    public Long getWishlistItemId() {
        return wishlistItemId;
    }

    public void setWishlistItemId(Long wishlistItemId) {
        this.wishlistItemId = wishlistItemId;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
