package com.inventory.service.Inventory.Service.dto.product;


import java.math.BigDecimal;
import java.util.UUID;

public record ProductInfoDto(
        UUID id,
        String name,
        BigDecimal price,
        int quantity
) {
}
