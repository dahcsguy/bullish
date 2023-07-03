package com.bullish.service.impl;

import com.bullish.model.Product;
import com.bullish.model.Promotion;
import com.bullish.repository.PromotionRepository;
import com.bullish.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    @Override
    public void addPromotion(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public Optional<Promotion> findMatchingPromotion(Product product) {
        for (Promotion nextPromotion : promotionRepository.findAll()) {
            if (nextPromotion.getProductId() == product.getProductId()) {
                return Optional.of(nextPromotion);
            }
        }
        return Optional.empty();
    }
}
