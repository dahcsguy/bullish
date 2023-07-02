package com.bullish.service.impl;

import com.bullish.exception.CartNotFoundException;
import com.bullish.exception.EmptyCartException;
import com.bullish.model.*;
import com.bullish.repository.OrderItemRepository;
import com.bullish.repository.OrderRepository;
import com.bullish.service.CartService;
import com.bullish.service.OrderCalculationService;
import com.bullish.service.OrderService;
import com.bullish.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderCalculationService orderCalculationService;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public void placeOrder(int cartId) {
        Optional<Cart> optionalCart = cartService.getCartById(cartId);

        if (optionalCart.isEmpty()) {
            throw new CartNotFoundException();
        }
        Cart cart = optionalCart.get();
        if (cart.isEmpty()) {
            throw new EmptyCartException();
        }
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0.0;
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            Product product = cartItem.getProduct();
            PurchasedProduct purchasedProduct = PurchasedProduct
                    .builder()
                    .price(product.getPrice())
                    .name(product.getName())
                    .build();
            orderItem.setProduct(purchasedProduct);
            orderItems.add(orderItem);
            orderItem.setSubtotal(orderCalculationService.calculateCartItem(cartItem));
            total += orderItem.getSubtotal();
            orderItemRepository.save(orderItem);
        }
        order.setOrderItemList(orderItems);
        order.setTotal(total);
        cartService.deleteCart(cartId);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders() {
        Iterable<Order> iter = orderRepository.findAll();
        List<Order> result = new ArrayList<>();
        iter.forEach(result::add);
        return result;
    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }
}
