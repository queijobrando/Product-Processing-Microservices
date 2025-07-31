package com.payment.service.Payment.Service.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderMessageDto(
        UUID id,
        String status,
        BigDecimal totalAmount,
        List<OrderMessageItems> items
) {
}
