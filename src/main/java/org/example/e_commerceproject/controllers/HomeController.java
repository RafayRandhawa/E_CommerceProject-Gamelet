package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.model.Product;
import org.example.e_commerceproject.service.ProductService;
import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String homePage(Model model) {
        // Check if a user is logged in using SessionService
        Object user = sessionService.getAttribute("user");
        model.addAttribute("isLoggedIn", user != null); // Pass login status to the view
        model.addAttribute("user", user); // Pass user object if available
        return "home"; // Render the `home.html` view
    }
    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam("categoryId") Long categoryId, Model model) {
        // Fetch the products for the given category
        List<Product> products = productService.getProductsByCategory(categoryId);

        // Add products and categoryId to the model
        model.addAttribute("products", products);
        model.addAttribute("categoryId", categoryId);

        return "products";
    }
}
