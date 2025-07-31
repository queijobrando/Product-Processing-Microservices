package com.inventory.service.Inventory.Service.dto.order;

import java.util.UUID;

public record OrderMessageItems(
        UUID productId,
        int quantity
) {
}
