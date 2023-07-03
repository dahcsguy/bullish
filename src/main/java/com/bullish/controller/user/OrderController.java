package com.bullish.controller.user;

import com.bullish.model.Order;
import com.bullish.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(value = "/carts/{cartId}/checkout")
    public ResponseEntity<?> checkoutCart(@PathVariable int cartId) {
        orderService.placeOrder(cartId);
        return ResponseEntity.ok("Checkout cart");
    }
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }
}
