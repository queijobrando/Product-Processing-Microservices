package com.productprocessing.orderservice.Order.Service.dto.neworder;

import com.productprocessing.orderservice.Order.Service.model.enun.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(
        UUID id,
        String customerName,
        Status status,
        BigDecimal totalAmount,
        LocalDateTime createdAt,
        List<OrderItemRequestDto> items
) {
}
