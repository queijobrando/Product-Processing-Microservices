package com.productprocessing.orderservice.Order.Service.dto.order;

import com.productprocessing.orderservice.Order.Service.model.enun.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseListDto(
        UUID id,
        String customerName,
        Status status,
        BigDecimal totalAmount,
        LocalDateTime createdAt
) {
}
