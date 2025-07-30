package com.productprocessing.orderservice.Order.Service.dto.neworder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

// Dto para os itens da lista
public record OrderItemRequestDto(
        @NotBlank
        String productId,
        @NotBlank
        String name,
        @NotNull
        int quantity,
        @NotNull
        BigDecimal price
) {}
