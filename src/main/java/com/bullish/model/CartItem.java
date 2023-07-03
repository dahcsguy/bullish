package com.bullish.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Product product;
    private int quantity;

}
