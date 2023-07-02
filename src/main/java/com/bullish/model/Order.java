package com.bullish.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "CUSTOMER_ORDER")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany
    private List<OrderItem> orderItemList;
    private double total;

}
