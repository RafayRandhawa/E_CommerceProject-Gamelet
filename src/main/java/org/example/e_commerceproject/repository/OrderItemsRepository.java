package org.example.e_commerceproject.repository;

import org.example.e_commerceproject.model.Order;
import org.example.e_commerceproject.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    // Find all OrderItems for a specific Order
    List<OrderItems> findByOrder_OrderId(Long orderId);

    // Find OrderItems by Order and Product
    OrderItems findByOrder_OrderIdAndProduct_ProductId(Long orderId, Long productId);

    OrderItems findByOrder(Order order);
}
