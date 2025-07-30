package com.productprocessing.orderservice.Order.Service.dto.order;

import java.util.List;
import java.util.UUID;

public record OrderMessageDto(
        UUID id,
        List<OrderMessageItems> items
) {
}
