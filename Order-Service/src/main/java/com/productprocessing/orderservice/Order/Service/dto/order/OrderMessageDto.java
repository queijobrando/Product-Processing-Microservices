package com.productprocessing.orderservice.Order.Service.dto.order;

import com.productprocessing.orderservice.Order.Service.model.enun.Status;

import java.util.List;
import java.util.UUID;

public record OrderMessageDto(
        UUID id,
        Status status,
        List<OrderMessageItems> items
) {
}
