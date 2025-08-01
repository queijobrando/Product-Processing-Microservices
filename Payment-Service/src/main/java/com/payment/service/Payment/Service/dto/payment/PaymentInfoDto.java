package com.payment.service.Payment.Service.dto.payment;

import com.payment.service.Payment.Service.model.enun.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentInfoDto(
        UUID id,
        Status status,
        UUID orderId,
        BigDecimal totalAmount,
        LocalDateTime createdAt
) {
}
