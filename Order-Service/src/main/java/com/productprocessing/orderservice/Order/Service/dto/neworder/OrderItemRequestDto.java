package com.productprocessing.orderservice.Order.Service.dto.neworder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

// Dto para os itens da lista
public record OrderItemRequestDto(
        @NotBlank
        UUID productId,
        @NotBlank
        String name,
        @NotNull
        int quantity,
        @NotNull
        BigDecimal price
) {}
