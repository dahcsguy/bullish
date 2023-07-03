package com.bullish.helper;

import com.bullish.model.BYGXPromotion;
import com.bullish.model.CartItem;
import com.bullish.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderCalculationHelperTest {

    @Test
    void calculateDiscount() {
        BYGXPromotion promotion = new BYGXPromotion();
        promotion.setMaxUse(1);
        promotion.setProductId(1);
        promotion.setPercent(100);
        promotion.setX(1);
        promotion.setY(1);
        Product product = new Product();
        product.setProductId(1);
        product.setPrice(100.0);
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2);
        cartItem.setProduct(product);
        double discount = OrderCalculationHelper.calculateDiscount(cartItem, Optional.of(promotion));
        assertEquals(100.0, discount);

        cartItem.setQuantity(1);
        discount = OrderCalculationHelper.calculateDiscount(cartItem, Optional.of(promotion));
        assertEquals(0.0, discount);

        cartItem.setQuantity(4);
        promotion.setMaxUse(2);
        discount = OrderCalculationHelper.calculateDiscount(cartItem, Optional.of(promotion));
        assertEquals(200.0, discount);
    }
}