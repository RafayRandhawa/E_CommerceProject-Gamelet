package org.example.e_commerceproject.model;

import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_seq")
    @SequenceGenerator(name = "cart_item_seq", sequenceName = "CART_ITEM_SEQ", allocationSize = 1)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(
            name = "cart_id",
            foreignKey = @ForeignKey(
                    name = "fk_cart_cart_item",
                    foreignKeyDefinition = "FOREIGN KEY (cart_id) REFERENCES Cart(cart_id) DEFERRABLE INITIALLY DEFERRED"
            )
    )
    private Cart cart;

    @OneToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(
                    name = "fk_product_cart_item",
                    foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES Product(product_id) DEFERRABLE INITIALLY DEFERRED"
            )
    )
    private Product product;

    private Integer quantity;
    private Double totalPrice;

    // Getters and Setters

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
