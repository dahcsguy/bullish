package com.bullish.helper;

import com.bullish.model.BYGXPromotion;
import com.bullish.model.CartItem;
import com.bullish.model.Promotion;

import java.util.Optional;

public class OrderCalculationHelper {

    public static double calculateDiscount(CartItem cartItem, Optional<Promotion> optionalPromotion) {
        double discount = 0.0;
        if (optionalPromotion.isEmpty()) {
            return discount;
        }
        Promotion promotion = optionalPromotion.get();
        switch (promotion) {
            case BYGXPromotion bygxPromotion:
                discount = PromotionCalculationHelper.getBYGXDiscount(cartItem, bygxPromotion);
                break;
            default:
                break;
        }
        return discount;
    }


}
