package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.model.Cart;
import org.example.e_commerceproject.model.CartItem;
import org.example.e_commerceproject.model.Product;
import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.service.CartService;
import org.example.e_commerceproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    /**
     * Display the cart page.
     */
    @GetMapping
    public String viewCart(Model model, @SessionAttribute("loggedInUser") User user) {
        Cart cart = cartService.getActiveCart(user);
        model.addAttribute("cartItems", cart.getCartItems());
        model.addAttribute("totalPrice", cart.getCartItems().stream()
                .mapToDouble(CartItem::getTotalPrice).sum());
        return "cart";
    }

    /**
     * Add an item to the cart.
     */
    @PostMapping("/add")
    public String addToCart(@SessionAttribute("loggedInUser") User user,
                            @RequestParam Long productId,
                            @RequestParam int quantity) {
        Product product = productService.getProductById(productId);
        cartService.addToCart(user, product, quantity);
        return "redirect:/cart";
    }

    /**
     * Checkout the cart.
     */
    @PostMapping("/checkout")
    public String checkout(@SessionAttribute("loggedInUser") User user) {
        Cart cart = cartService.getActiveCart(user);
        cart.setActive(false); // Deactivate the cart
        cartService.save(cart); // Save updated cart
        return "redirect:/checkout-success";
    }
}

