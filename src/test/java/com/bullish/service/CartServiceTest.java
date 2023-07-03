package com.bullish.service;

import com.bullish.model.Cart;
import com.bullish.repository.CartRepository;
import com.bullish.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartServiceImpl cartService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCartById() {
        Optional<Cart> optionalCart = Optional.of(new Cart());
        when(cartRepository.findById(Mockito.any())).thenReturn(optionalCart);
        Optional<Cart> testCart = cartService.getCartById(12);
        assertEquals(testCart, optionalCart);
    }

    @Test
    void addToCart() {

    }

    @Test
    void updateCartItem() {
    }

    @Test
    void deleteCartItem() {
    }

    @Test
    void deleteCart() {
    }

    @Test
    void createCart() {
    }
}