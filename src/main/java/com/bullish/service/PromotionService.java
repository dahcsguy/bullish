package com.bullish.service;

import com.bullish.model.Product;
import com.bullish.model.Promotion;

import java.util.Optional;

public interface PromotionService {

    void addPromotion(Promotion promotion);

    Optional<Promotion> findMatchingPromotion(Product product);
}
