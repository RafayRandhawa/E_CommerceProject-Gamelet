package org.example.e_commerceproject.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;



@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "CART_SEQ", allocationSize = 1)
    private Long cartId;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "fk_user_cart",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES Users(id) DEFERRABLE INITIALLY DEFERRED"
            )
    )
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    // Getters and Setters

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
