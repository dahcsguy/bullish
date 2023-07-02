package com.bullish.service;

import com.bullish.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void placeOrder(int cartId);

    List<Order> getOrders();

    Optional<Order> getOrderById(int orderId);
}
