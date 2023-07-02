package com.bullish.service;

import com.bullish.model.CartItem;

public interface OrderCalculationService {

    double calculateCartItem(CartItem cartItem);
}
