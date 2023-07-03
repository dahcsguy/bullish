package com.bullish.service;

import com.bullish.model.Cart;
import com.bullish.model.CartItem;
import com.bullish.model.Order;

import java.util.List;
import java.util.Optional;

public interface CartService {

    Optional<Cart> getCartById(int cartId);

    void addToCart(int cartId, CartItem cartItem);

    void deleteCartItem(int cartItemId);

    void deleteCartItem(int cartId, int cartItemId);

    void deleteCart(int cartId);

    void createCart();

    List<CartItem> getCartsWithProduct(int productId);
}
