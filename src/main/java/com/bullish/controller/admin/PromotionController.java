package com.bullish.controller.admin;

import com.bullish.model.Promotion;
import com.bullish.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/promotions")
public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPromotion(@RequestBody Promotion promotion) {
        promotionService.addPromotion(promotion);
        return ResponseEntity.ok("Promotion saved");
    }

}
