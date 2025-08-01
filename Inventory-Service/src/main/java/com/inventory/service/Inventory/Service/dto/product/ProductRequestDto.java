package com.inventory.service.Inventory.Service.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDto(
        @NotBlank
        String name,
        @NotNull
        BigDecimal price,
        @NotNull
        int quantity
) {
}
