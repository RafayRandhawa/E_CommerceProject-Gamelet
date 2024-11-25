package org.example.e_commerceproject.repository;

import org.example.e_commerceproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find Products by Name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Find Products by Category ID
    List<Product> findByCategory_CategoryId(Long categoryId);

    // Find Products by Price Greater Than a Given Value
    List<Product> findByPriceGreaterThan(Double price);

    // Find Products by Price Less Than a Given Value
    List<Product> findByPriceLessThan(Double price);

    // Find Products by Price Range
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Find Products by Stock Quantity Greater Than
    List<Product> findByStockQuantityGreaterThan(Integer quantity);

    // Find Products by Name and Category ID
    List<Product> findByNameContainingIgnoreCaseAndCategory_CategoryId(String name, Long categoryId);

    // Count Products by Category ID
    Long countByCategory_CategoryId(Long categoryId);

    // Find Products Created After a Specific Date
    List<Product> findByCreatedAtAfter(LocalDateTime date);

    // Find Products Created Before a Specific Date
    List<Product> findByCreatedAtBefore(LocalDateTime date);
}
