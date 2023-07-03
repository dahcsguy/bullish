package com.bullish.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "ItemPromotion")
public abstract class Promotion {
    @Id
    @GeneratedValue
    private int promotionId;
    private int productId;
    @Positive
    private int maxUse;

}