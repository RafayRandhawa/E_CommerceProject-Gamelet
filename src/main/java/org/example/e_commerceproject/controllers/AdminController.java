package org.example.e_commerceproject.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.e_commerceproject.model.*;
import org.example.e_commerceproject.repository.OrderItemsRepository;
import org.example.e_commerceproject.service.AdminService;
import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    // User management endpoints
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = adminService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/users")
    public ModelAndView createUser (@RequestBody User user) {
        System.out.println(user.getAddress());
        user.setCreated_at(LocalDateTime.now());
        adminService.saveUser(user);
        ModelAndView mav = new ModelAndView("adminPanel"); // "my-page" is the name of the HTML file in templates
        mav.addObject("message", "Hello from RestController!");
        return mav;
    }

    @PutMapping("/users/{id}")
    public void updateUser (@PathVariable int id, @RequestBody User user) {
        User updatedUser  = adminService.updateUser (id, user);
        ResponseEntity.ok(updatedUser );
//        ModelAndView mav = new ModelAndView("adminPanel"); // "my-page" is the name of the HTML file in templates
//        mav.addObject("message", "Hello from RestController!");
//        return mav;
    }

    @DeleteMapping("/users/{id}")
    public ModelAndView deleteUser (@PathVariable int id) {
        adminService.deleteUser (id);
        ResponseEntity.noContent().build();
        ModelAndView mav = new ModelAndView("adminPanel"); // "my-page" is the name of the HTML file in templates
        mav.addObject("message", "Hello from RestController!");
        return mav;
    }

    // Product management endpoints
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return adminService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = adminService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return adminService.saveProduct(product);
    }
    @PostMapping("/products/insertAll")
    public List<Product> createListProducts(@RequestBody List<Product> products) {
        // Save each product and collect the saved products into a list
        return products.stream()
                .map(adminService::saveProduct)
                .toList(); // Using `toList()` from Java 16 onwards
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = adminService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        adminService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Order management endpoints
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = adminService.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        adminService.updateOrder(id, order);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/orders/{id}/cancel")
    public void cancelOrder(@PathVariable Long id, @RequestBody Order order) {
        adminService.cancelOrder(id);

    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        adminService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<OrderItems>> getOrderProducts(@PathVariable Long id) {
        List<OrderItems> orderItems = orderItemsRepository.findByOrder_OrderId(id);

        if (orderItems.isEmpty()) {
            // Return 404 Not Found if no items are found
            return ResponseEntity.notFound().build();
        }

        // Return 200 OK with the list of order items
        return ResponseEntity.ok(orderItems);
    }

    // Review management endpoints
    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return adminService.getAllReviews();
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        adminService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/reviews/{id}")
    public ResponseEntity<Void> updateReview(@PathVariable Long id){
        adminService.updateReview(id);
        return ResponseEntity.noContent().build();
    }

    // Payment management endpoints
    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return adminService.getAllPayments();
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = adminService.getPaymentById(id);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    // Category management endpoints
    @GetMapping("/categories")
    public List<Category> getAllCategories() {

        return adminService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = adminService.getCategoryById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PostMapping("/categories/insertAll")
    public void createCategoriesList(@RequestBody List<Category> categories) {
        categories.forEach(adminService::saveCategory);
    }
    @PostMapping("/categories")
    public void createCategories(@RequestBody Category category)
    {

        adminService.saveCategory(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = adminService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        adminService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shippings")
    public List<Shipping> getAllShippings() {
        return adminService.getAllShippings();
    }
    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

}