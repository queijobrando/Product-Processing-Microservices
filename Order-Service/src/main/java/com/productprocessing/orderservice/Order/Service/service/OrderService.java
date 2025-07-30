package com.productprocessing.orderservice.Order.Service.service;

import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderRequestDto;
import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderResponseDto;
import com.productprocessing.orderservice.Order.Service.dto.order.OrderResponseListDto;
import com.productprocessing.orderservice.Order.Service.mapper.OrderMapper;
import com.productprocessing.orderservice.Order.Service.model.Order;
import com.productprocessing.orderservice.Order.Service.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderMapper orderMapper;

    @Transactional
    public OrderResponseDto newOrder(OrderRequestDto dto){
        Order order = orderMapper.toEntity(dto);
        order.setTotalAmount(totalAmount(order));

        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    public OrderResponseDto getOrder(UUID id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invalid or nonexistent order"));

        return orderMapper.toDto(order);
    }

    public List<OrderResponseListDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toList(orders);
    }

    public BigDecimal totalAmount(Order order){
        return order.getItems().stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}



