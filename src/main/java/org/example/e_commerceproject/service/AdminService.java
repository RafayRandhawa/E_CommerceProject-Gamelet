package org.example.e_commerceproject.service;
import org.example.e_commerceproject.model.Category;
import org.example.e_commerceproject.model.Order;
import org.example.e_commerceproject.model.Payment;
import org.example.e_commerceproject.model.Product;
import org.example.e_commerceproject.model.Review;
import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.repository.CategoryRepository;
import org.example.e_commerceproject.repository.OrderRepository;
import org.example.e_commerceproject.repository.PaymentRepository;
import org.example.e_commerceproject.repository.ProductRepository;
import org.example.e_commerceproject.repository.ReviewRepository;
import org.example.e_commerceproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Product saveProduct(Product product) {
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

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        category.setCategoryId(id); // Ensure the ID is set for the update
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}