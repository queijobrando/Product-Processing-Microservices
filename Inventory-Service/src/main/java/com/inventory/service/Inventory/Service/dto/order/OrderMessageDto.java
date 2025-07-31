package com.inventory.service.Inventory.Service.dto.order;


import java.util.List;
import java.util.UUID;

public record OrderMessageDto(
        UUID id,
        String status,
        List<OrderMessageItems> items
) {
}
