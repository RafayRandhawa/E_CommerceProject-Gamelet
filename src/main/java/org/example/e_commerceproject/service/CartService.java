package org.example.e_commerceproject.service;

import org.example.e_commerceproject.model.Cart;
import org.example.e_commerceproject.model.CartItem;
import org.example.e_commerceproject.model.Product;
import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.repository.CartItemRepository;
import org.example.e_commerceproject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    /**
     * Get or create an active cart for a user.
     */
    public Cart getOrCreateCart(User user) {
        Optional<Cart> cartOptional = cartRepository.findByUserAndIsActive(user, true);
        return cartOptional.orElseGet(() -> {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setCreatedAt(LocalDateTime.now());
            cart.setUpdatedAt(LocalDateTime.now());
            cart.setActive(true);
            return cartRepository.save(cart);
        });
    }

    /**
     * Add a product to the cart or update an existing item's quantity.
     */
    public void addToCart(User user, Product product, int quantity) {
        Cart cart = getOrCreateCart(user);

        if(!cart.getCartItems().isEmpty()){
            Optional<CartItem> existingItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getProductId().equals(product.getProductId()))
                    .findFirst();

            if (existingItem.isPresent()) {
                CartItem cartItem = existingItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(cartItem.getQuantity() * product.getPrice());
                cartItemRepository.save(cartItem);
            } else {
                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setProduct(product);
                newItem.setQuantity(quantity);
                newItem.setTotalPrice(product.getPrice() * quantity);
                cartItemRepository.save(newItem);
            }
        }
        else{
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setTotalPrice(product.getPrice() * quantity);
            cartItemRepository.save(newItem);
        }

        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }

    /**
     * Retrieve all items in the user's active cart.
     */
    public Cart getActiveCart(User user) {
        return cartRepository.findByUserAndIsActive(user, true)
                .orElseThrow(() -> new IllegalArgumentException("No active cart found for the user."));
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}

