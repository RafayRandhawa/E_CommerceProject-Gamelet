package org.example.e_commerceproject.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.e_commerceproject.model.Order;
import org.example.e_commerceproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.e_commerceproject.service.SessionService;
import org.example.e_commerceproject.service.OrderService;
import org.example.e_commerceproject.service.UserService;
import org.example.e_commerceproject.service.ReviewService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("profile")
public class ProfileController {

    private final SessionService sessionService;
    private final OrderService orderService;
    private final UserService userService;

    private final ReviewService reviewService;

    @Autowired
    public ProfileController(SessionService sessionService, OrderService orderService, UserService userService, ReviewService reviewService) {
        this.sessionService = sessionService;
        this.orderService = orderService;
        this.userService = userService; // Assuming UserService is injected here as well
        this.reviewService = reviewService;
    }


    @GetMapping("/view")
    public String profilePage(Model model) {
        User user = (User) sessionService.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect to log in if user is not logged in
        }
        List<Order> orders = orderService.getOrdersByUser(user.getId());
        model.addAttribute("orders",orders);
        model.addAttribute("user", user);

        model.addAttribute("isLoggedIn", sessionService.getAttribute("user") != null);
        return "profile"; // Maps to `profile.html`
    }
    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("user") User updatedUser, HttpSession session, Model model) {
        // Get the currently logged-in user
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Ensure the updated user has the correct ID
        updatedUser.setId(currentUser.getId());
        updatedUser.setPassword(currentUser.getPassword());
        updatedUser.setRole(currentUser.getRole());
        updatedUser.setCreated_at(currentUser.getCreated_at());
        updatedUser.setUpdated_at(LocalDateTime.now());

        try{
            System.out.println("User to update: " + updatedUser);
            // Save updated user to the database
            userService.updateUser(updatedUser);

            // Update session data
            session.setAttribute("user", updatedUser);

            // Add success message to model
            model.addAttribute("successMessage", "Profile updated successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            model.addAttribute("errorMessage", "Error updating profile: " + e.getMessage());
        }

        return "redirect:/profile/view"; // Redirect to profile page
    }

    @GetMapping("/order/details/{id}")
    @ResponseBody
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long id) {
        Order order = orderService.getOrderById(id); // Fetch order by ID
//        if (order == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("orderId", order.getOrderId());
//        response.put("orderDate", order.getOrderDate());
//        response.put("status", order.getStatus());
//        response.put("user", Map.of("id", order.getUser().getId(), "First name", order.getUser().getFirstname()));
//        response.put("paymentType", order.getPayment().getPaymentType()); // Fetch from Payment table
//
//        return ResponseEntity.ok(response);

        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PostMapping("/submit-feedback")
    @ResponseBody
    public ResponseEntity<String> submitFeedback(@RequestBody Map<String, Object> feedbackData) {
        try {
            Long orderId = Long.valueOf(feedbackData.get("orderId").toString());
            int rating = Integer.parseInt(feedbackData.get("rating").toString());
            String reviewText = feedbackData.get("reviewText").toString();

            // Ensure the order exists and is linked to the current user
            Order order = orderService.getOrderById(orderId);
            if (order == null || !(order.getUser().getId() == ((User)sessionService.getAttribute("user")).getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized or invalid order.");
            }

            // Save the feedback into the Reviews table
            User user = (User)sessionService.getAttribute("user");
            reviewService.saveReview(orderId, user, rating, reviewText);

            return ResponseEntity.ok("Feedback submitted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting feedback.");
        }
    }


}

