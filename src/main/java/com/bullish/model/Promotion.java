package com.bullish.model;

import com.bullish.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "ItemPromotion")
public class Promotion {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Product product;
    @Positive
    private int maxUse;

}