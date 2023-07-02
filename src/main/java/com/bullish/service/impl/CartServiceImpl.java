package com.bullish.service.impl;

import com.bullish.exception.CartNotFoundException;
import com.bullish.model.Cart;
import com.bullish.model.CartItem;
import com.bullish.repository.CartItemRepository;
import com.bullish.repository.CartRepository;
import com.bullish.service.CartService;
import com.bullish.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductService productService;


    @Override
    public Optional<Cart> getCartById(int cartId) {
        return this.cartRepository.findById(cartId);
    }

    @Override
    public void addToCart(int cartId, CartItem cartItem) {
        this.getCartById(cartId).ifPresentOrElse(cart -> {
            cart.addToCart(cartItem);
            cartItemRepository.save(cartItem);
        }, () -> {
            throw new CartNotFoundException();
        });
    }

    @Override
    public void updateCartItem(int cartId, CartItem cartItem) {
        this.getCartById(cartId).ifPresentOrElse(cart -> {
            cart.updateItemInCart(cartItem);
            cartItemRepository.save(cartItem);
        }, () -> {
            throw new CartNotFoundException();
        });
    }

    @Override
    public void deleteCartItem(int cartId, int cartItemId) {
        this.getCartById(cartId).ifPresentOrElse(cart -> {
            cart.removeFromCart(cartItemId);
            cartItemRepository.deleteById(cartItemId);
        }, () -> {
            throw new CartNotFoundException();
        });
    }

    @Override
    public void deleteCart(int cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void createCart() {
        cartRepository.save(new Cart());
    }

}
