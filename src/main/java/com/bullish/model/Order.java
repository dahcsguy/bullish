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
    private int orderId;
    @OneToMany
    private List<OrderItem> orderItemList;
    private double grantTotal;
    private double subtotal;
    private double discount;

}
