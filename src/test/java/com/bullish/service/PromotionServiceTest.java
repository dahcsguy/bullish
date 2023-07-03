package com.bullish.service;

import com.bullish.model.BYGXPromotion;
import com.bullish.model.Product;
import com.bullish.model.Promotion;
import com.bullish.repository.PromotionRepository;
import com.bullish.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PromotionServiceTest {


    @Mock
    PromotionRepository promotionRepository;
    @InjectMocks
    PromotionServiceImpl promotionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findMatchingPromotion() {
        Product product = new Product();
        product.setProductId(1);
        BYGXPromotion promotion = new BYGXPromotion();
        promotion.setProductId(1);
        when(promotionRepository.findAll()).thenReturn(Arrays.asList(promotion));

        Optional<Promotion> optional = promotionService.findMatchingPromotion(product);
        assertEquals(promotion, optional.get());

    }
}