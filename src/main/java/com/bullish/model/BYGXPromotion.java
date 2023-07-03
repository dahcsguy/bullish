package com.bullish.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Entity
@Data
final public class BYGXPromotion extends Promotion {
    @Positive
    private int y;
    @Positive
    private int x;
    @Range(min = 0, max = 100)
    private double percent;

}
