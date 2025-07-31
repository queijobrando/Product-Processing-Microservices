package com.payment.service.Payment.Service.dto;

import java.util.UUID;

public record OrderMessageItems(
        UUID productId,
        int quantity
) {
}
