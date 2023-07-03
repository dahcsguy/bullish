package com.bullish.service;

import com.bullish.exception.CartNotFoundException;
import com.bullish.model.Cart;
import com.bullish.model.CartItem;
import com.bullish.repository.CartItemRepository;
import com.bullish.repository.CartRepository;
import com.bullish.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    CartRepository cartRepository;
    @Mock
    CartItemRepository cartItemRepository;
    @InjectMocks
    CartServiceImpl cartService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCartById() {
        Optional<Cart> optionalCart = Optional.of(new Cart());
        when(cartRepository.findById(Mockito.anyInt())).thenReturn(optionalCart);
        Optional<Cart> testCart = cartService.getCartById(12);
        assertEquals(testCart, optionalCart);
    }

    @Test
    void addToCart_success() {
        CartItem cartItem = new CartItem();
        Cart cart = new Cart();
        when(cartRepository.findById(anyInt())).thenReturn(Optional.of(cart));
        cartService.addToCart(1, cartItem);
        assertEquals(cart.getCartItems().get(0), cartItem);
    }

    @Test
    void addToCart_failure() {
        assertThrows(CartNotFoundException.class, () -> {
            cartService.addToCart(1, new CartItem());
        });
    }

    @Test
    void deleteCartItem() {
        Cart cart = new Cart();

        when(cartRepository.findById(anyInt())).thenReturn(Optional.of(cart));
        when(cartRepository.findAll()).thenReturn(Arrays.asList(cart));
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(1);
        cartService.addToCart(1, cartItem);
        assertFalse(cart.isEmpty());
        cartService.deleteCartItem(1, 1);
        assertTrue(cart.isEmpty());

    }

    @Test
    void deleteCart() {
        doNothing().when(cartRepository).deleteById(anyInt());
        cartService.deleteCart(1);
        verify(cartRepository).deleteById(1);
    }

    @Test
    void createCart() {
        cartService.createCart();
        verify(cartRepository).save(any());
    }

}