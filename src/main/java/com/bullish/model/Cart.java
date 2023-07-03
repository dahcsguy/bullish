package com.bullish.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    private int cartId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public void addToCart(CartItem cartItem) {
        cartItem.setCart(this);
        cartItems.add(cartItem);
    }

    public void removeFromCart(int cartItemId) {
        cartItems.removeIf(item -> item.getCartItemId() == cartItemId);
    }

    public boolean containsExistingProduct(int productId) {
        return cartItems.stream().anyMatch(cartItem -> cartItem.getProduct().getProductId() == productId);
    }

}
