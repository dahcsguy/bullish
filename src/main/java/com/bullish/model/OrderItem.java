package com.bullish.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private PurchasedProduct product;
    private int quantity;
    private double subtotal;
    @ManyToOne
    private Promotion promotion;

}
