package com.bullish.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void isEmpty() {
        Cart cart = new Cart();
        assertTrue(cart.isEmpty());
        cart.addToCart(new CartItem());
        assertFalse(cart.isEmpty());
    }

    @Test
    void addToCart() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        Product product = new Product();
        product.setName("apple");
        product.setPrice(12.3);
        cartItem.setQuantity(12);
        cartItem.setProduct(product);
        cart.addToCart(cartItem);
        assertEquals(cart.getCartItems().get(0).getProduct(), product);
        assertEquals(cart.getCartItems().get(0).getQuantity(), 12);
    }

    @Test
    void removeFromCart() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        Product product = new Product();
        product.setName("apple");
        product.setPrice(12.3);
        cartItem.setQuantity(12);
        cartItem.setProduct(product);
        cart.addToCart(cartItem);
        assertFalse(cart.isEmpty());
        cart.removeFromCart(0);
        assertTrue(cart.isEmpty());

    }

}