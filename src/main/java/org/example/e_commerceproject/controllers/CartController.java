package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.model.Cart;
import org.example.e_commerceproject.model.CartItem;
import org.example.e_commerceproject.model.Product;
import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.repository.CartRepository;
import org.example.e_commerceproject.service.CartService;
import org.example.e_commerceproject.service.ProductService;
import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private CartRepository cartRepository;

    /**
     * Add an item to the cart.
     */
    @PostMapping("/add")
    public void addToCart(@SessionAttribute("user") User user,
                            @RequestParam Long productId,
                            @RequestParam int quantity) {
        Product product = productService.getProductById(productId);
        try{
            cartService.addToCart(user, product, quantity);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checkout the cart.
     */
//    @PostMapping("/checkout")
//    public String checkout(@SessionAttribute("user") User user) {
//        Cart cart = cartService.getActiveCart(user);
//        cart.setActive(false); // Deactivate the cart
//        cartService.save(cart); // Save updated cart
//        return "redirect:/checkout-success";
//    }

    @GetMapping("/cart")
    public Cart getCart(@SessionAttribute("user") User user) {
        return cartRepository.findByUserId(user.getId());
    }
    @GetMapping("/items")
    public List<CartItem> getCartItems() {
        // Retrieve cart items from the database
        return cartService.getCartItems(((User)sessionService.getAttribute("user")).getId());
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCartItem(@PathVariable Long id){
        cartService.deleteCartItem(id);
    }
}

