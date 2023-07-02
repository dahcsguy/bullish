package com.bullish.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
final public class BYGXPromotion extends Promotion {
    @Positive
    private int y;
    @Positive
    private int x;
    @Positive
    private double percent;

}
