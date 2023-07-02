package com.bullish.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Entity
@Builder
public class PurchasedProduct {
    @Id
    @GeneratedValue
    private int id;
    private String name;
}
