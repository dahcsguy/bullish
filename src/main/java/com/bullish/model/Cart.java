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
    private int id;
    @ManyToMany
    private List<CartItem> cartItems = new ArrayList<>();

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void removeFromCart(int cartItemId) {
        cartItems.removeIf(item -> item.getId() == cartItemId);
    }

    public void updateItemInCart(CartItem cartItem) {
        cartItems.stream().findAny().ifPresent(item -> {
            item.setQuantity(cartItem.getQuantity());
        });
    }

}
