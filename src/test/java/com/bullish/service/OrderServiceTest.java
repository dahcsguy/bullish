package com.bullish.service;

import com.bullish.exception.EmptyCartException;
import com.bullish.model.Cart;
import com.bullish.model.CartItem;
import com.bullish.model.Order;
import com.bullish.model.Product;
import com.bullish.repository.OrderItemRepository;
import com.bullish.repository.OrderRepository;
import com.bullish.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private CartService cartService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    PromotionService promotionService;

    @InjectMocks
    OrderServiceImpl orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    void placeOrder_emptyCart() {
        Cart cart = new Cart();
        when(cartService.getCartById(1)).thenReturn(Optional.of(cart));

        assertThrows(EmptyCartException.class, () -> {
            orderService.placeOrder(1);
        });
    }

    @Test()
    void placeOrder_success() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        Product product = new Product();
        product.setPrice(12.3);
        cartItem.setProduct(product);
        cart.addToCart(cartItem);
        when(cartService.getCartById(1)).thenReturn(Optional.of(cart));

        orderService.placeOrder(1);
        verify(orderRepository, times(1)).save(any());
    }


    @Test
    void getOrders() {
        List<Order> orders = new ArrayList<>();

        when(orderRepository.findAll()).thenReturn(orders);
        orderService.getOrders();
        verify(orderRepository, times(1)).findAll();
    }


}