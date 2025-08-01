package com.payment.service.Payment.Service.mapper;

import com.payment.service.Payment.Service.dto.OrderMessageDto;
import com.payment.service.Payment.Service.dto.OrderMessageItems;
import com.payment.service.Payment.Service.dto.payment.PaymentInfoDto;
import com.payment.service.Payment.Service.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "integrated", ignore = true)
    Payment toEntity(OrderMessageDto orderMessageDto);

    PaymentInfoDto toDto(Payment payment);

    @Mapping(target = "id", source = "orderId")
    @Mapping(target = "status", expression = "java(payment.getStatus().getName())")
    @Mapping(target = "items", expression = "java(java.util.Collections.emptyList())")
    OrderMessageDto toMessageDto(Payment payment);

}
