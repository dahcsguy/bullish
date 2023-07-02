package com.bullish.service.impl;

import com.bullish.helper.PromotionCalculationHelper;
import com.bullish.model.BYGXPromotion;
import com.bullish.model.CartItem;
import com.bullish.model.Product;
import com.bullish.model.Promotion;
import com.bullish.service.OrderCalculationService;
import com.bullish.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderCalculationServiceImpl implements OrderCalculationService {
    @Autowired
    PromotionService promotionService;


    @Override
    public double calculateCartItem(CartItem cartItem) {
        Product product = cartItem.getProduct();
        Optional<Promotion> optionalPromotion = promotionService.findMatchingPromotion(cartItem.getProduct());
        double subtotal = cartItem.getQuantity() * product.getPrice();
        if (optionalPromotion.isEmpty()) {
            return subtotal;
        }
        Promotion promotion = optionalPromotion.get();
        double discount = 0.0;
        switch (promotion) {
            case BYGXPromotion bygxPromotion:
                discount = PromotionCalculationHelper.getBYGXDiscount(cartItem, bygxPromotion);
                break;
            default:
                break;
        }
        return subtotal - discount;
    }


}
