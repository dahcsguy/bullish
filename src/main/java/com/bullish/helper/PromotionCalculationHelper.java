package com.bullish.helper;

import com.bullish.model.BYGXPromotion;
import com.bullish.model.CartItem;

public class PromotionCalculationHelper {

    public static double getBYGXDiscount(CartItem cartItem, BYGXPromotion promotion) {
        int totalCartQuantity = cartItem.getQuantity();
        double totalDiscount = 0.0;
        for (int i = 0; i < promotion.getMaxUse() && totalCartQuantity > 0; i++) {
            if (totalCartQuantity <= promotion.getY()) {
                break;
            }
            totalCartQuantity -= promotion.getY();
            totalDiscount += Math.min(promotion.getX(), totalCartQuantity) * cartItem
                    .getProduct()
                    .getPrice() * (promotion.getPercent() / 100);
            totalCartQuantity -= promotion.getX();
        }

        return totalDiscount;

    }

}
