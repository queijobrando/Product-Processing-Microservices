package com.productprocessing.orderservice.Order.Service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class OrderItem {

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

}
