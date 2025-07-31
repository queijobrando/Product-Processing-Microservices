package com.productprocessing.orderservice.Order.Service.mapper;

import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderRequestDto;
import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderResponseDto;
import com.productprocessing.orderservice.Order.Service.dto.order.OrderMessageDto;
import com.productprocessing.orderservice.Order.Service.dto.order.OrderResponseListDto;
import com.productprocessing.orderservice.Order.Service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "integrated", ignore = true)
    Order toEntity(OrderRequestDto dto);

    OrderResponseDto toDto(Order order);

    List<OrderResponseListDto> toList(List<Order> orders);

    //Message
    @Mapping(target = "status", expression = "java(order.getStatus().name())")
    OrderMessageDto toMessageDto(Order order);

}
