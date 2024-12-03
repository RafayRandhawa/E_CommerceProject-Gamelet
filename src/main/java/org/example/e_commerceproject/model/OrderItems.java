package org.example.e_commerceproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_ITEM_SEQ", allocationSize = 1)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            foreignKey = @ForeignKey(
                    name = "fk_order_order_item",
                    foreignKeyDefinition = "FOREIGN KEY (order_id) REFERENCES Orders(order_id) DEFERRABLE INITIALLY DEFERRED"
            )
    )
    @JsonBackReference
    private Order order;

    @OneToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(
                    name = "fk_product_order_item",
                    foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES Product(product_id) DEFERRABLE INITIALLY DEFERRED"
            )
    )
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double totalPrice;

    // Getters and Setters


    public Long getOrderItemId() {
        return orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setOrder(Order order) {
        this.order = order;
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
