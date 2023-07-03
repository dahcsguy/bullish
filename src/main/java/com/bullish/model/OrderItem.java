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
    private int orderItemId;
    String productName;
    double price;
    private int quantity;
    private double subtotal;
    @ManyToOne
    private Promotion promotion;
    private double discount;

}
