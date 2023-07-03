package com.bullish.service.impl;

import com.bullish.exception.CartNotFoundException;
import com.bullish.model.Cart;
import com.bullish.model.CartItem;
import com.bullish.repository.CartItemRepository;
import com.bullish.repository.CartRepository;
import com.bullish.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;


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
    public void deleteCartItem(int cartItemId) {
        for (CartItem cartItem : cartItemRepository.findAll()) {
            if (cartItem.getCartItemId() == cartItemId) {
                cartItem.getCart().removeFromCart(cartItemId);
                cartItemRepository.deleteById(cartItemId);
            }
        }
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

    @Override
    public List<CartItem> getCartsWithProduct(int productId) {
        List<CartItem> cartItems = new ArrayList();
        for(CartItem cartItem : cartItemRepository.findAll()) {
            if(cartItem.getProduct().getProductId() == productId) {
                cartItems.add(cartItem);
            }
        }
        return cartItems;
    }


}
