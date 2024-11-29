package org.example.e_commerceproject.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.e_commerceproject.model.*;
import org.example.e_commerceproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ShippingRepository shippingRepository;

    // User management
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void saveUser (User user) {
        userRepository.save(user);
    }

    public User updateUser (int id, User user) {
        user.setId(id); // Ensure the ID is set for the update
        return userRepository.save(user);
    }

    public void deleteUser (int id) {
        userRepository.deleteById(id);
    }

    // Product management
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product saveProduct(Product product) {

        if (product.getCategory() != null && product.getCategory().getCategoryId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            product.setCategory(category);
            product.setCreatedAt(LocalDateTime.now());
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        product.setProductId(id); // Ensure the ID is set for the update
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Order management
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void updateOrder(Long id, Order order) {
        order.setOrderId(id); // Ensure the ID is set for the update
        orderRepository.save(order);
    }
    @Transactional
    public void cancelOrder(Long orderId) {
        // Retrieve the order by ID
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " not found"));

        // Validate the current status of the order
        if ("Cancelled".equalsIgnoreCase(order.getStatus())) {
            throw new IllegalStateException("Order is already cancelled.");
        }

        // Update the order status and timestamp
        order.setStatus("Cancelled");
        order.setUpdatedAt(LocalDateTime.now());

        // Save the updated order to the database
        orderRepository.save(order);
    }
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Review management
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    public void updateReview(Long id){reviewRepository.updateIsApprovedById(id,true);}

    // Payment management
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    // Category management
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        category.setCategoryId(id); // Ensure the ID is set for the update
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    public List<Shipping> getAllShippings(){
        return shippingRepository.findAll();
    }
}