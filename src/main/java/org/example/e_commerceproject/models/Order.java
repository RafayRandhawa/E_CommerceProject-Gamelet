package org.example.e_commerceproject.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "ORDER_SEQ", allocationSize = 1)
    private Long orderId;

    private LocalDateTime orderDate;
    private String status;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "fk_user_order",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES Users(id) DEFERRABLE INITIALLY DEFERRED"
            )
    )
    private User user;

    @OneToOne
    @JoinColumn(
            name = "shipping_id",
            foreignKey = @ForeignKey(
                    name = "fk_shipping_order",
                    foreignKeyDefinition = "FOREIGN KEY (shipping_id) REFERENCES Shipping(shipping_Id) DEFERRABLE INITIALLY DEFERRED"
            )
    )
    private Shipping shipping;

    @OneToOne
    @JoinColumn(
            name = "payment_id",
            foreignKey = @ForeignKey(
                    name = "fk_payment_order",
                    foreignKeyDefinition = "FOREIGN KEY (payment_id) REFERENCES Payment(payment_Id) DEFERRABLE INITIALLY DEFERRED"
            )
    )
    private Payment payment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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
}
