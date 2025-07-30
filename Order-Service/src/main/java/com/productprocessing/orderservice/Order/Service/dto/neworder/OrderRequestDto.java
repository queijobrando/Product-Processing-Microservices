package com.productprocessing.orderservice.Order.Service.dto.neworder;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDto(
        @NotBlank
        String customerName,
        @NotNull
        List<OrderItemRequestDto> items
) {
}
