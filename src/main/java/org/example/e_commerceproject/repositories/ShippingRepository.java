package org.example.e_commerceproject.repositories;

import org.example.e_commerceproject.models.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    // Find Shipping Information by Postal Code
    List<Shipping> findByPostalCode(String postalCode);

    // Find Shipping Information by Country
    List<Shipping> findByCountry(String country);

    // Find Shipping Information by City
    List<Shipping> findByCity(String city);

    // Find Shipping Information by State
    List<Shipping> findByState(String state);

    // Find Shipments by Address
    List<Shipping> findByAddressContaining(String address);

    // Find Shipments that are Shipped after a Specific Date
    List<Shipping> findByShippingDateAfter(LocalDateTime date);

    // Find Shipments that are Shipped before a Specific Date
    List<Shipping> findByShippingDateBefore(LocalDateTime date);

    // Find a Single Shipping Record by Shipping ID
    Optional<Shipping> findByShippingId(Long shippingId);
}
