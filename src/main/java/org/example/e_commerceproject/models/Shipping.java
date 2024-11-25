package org.example.e_commerceproject.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_seq")
    @SequenceGenerator(name = "shipping_seq", sequenceName = "SHIPPING_SEQ", allocationSize = 1)
    private Long shippingId;

    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private LocalDateTime shippingDate;

    // Getters and Setters

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }
}
