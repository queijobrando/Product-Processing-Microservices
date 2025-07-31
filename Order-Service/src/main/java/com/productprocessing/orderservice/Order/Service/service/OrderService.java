package com.productprocessing.orderservice.Order.Service.service;

import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderRequestDto;
import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderResponseDto;
import com.productprocessing.orderservice.Order.Service.dto.order.OrderResponseListDto;
import com.productprocessing.orderservice.Order.Service.mapper.OrderMapper;
import com.productprocessing.orderservice.Order.Service.model.Order;
import com.productprocessing.orderservice.Order.Service.model.enun.Status;
import com.productprocessing.orderservice.Order.Service.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderMapper orderMapper;

    @Autowired
    public MessageRabbitMq messageRabbitMq;

    @Transactional
    public OrderResponseDto newOrder(OrderRequestDto dto){
        Order order = orderMapper.toEntity(dto);
        order.setTotalAmount(totalAmount(order));

        orderRepository.save(order);
        message(order); // message

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

    public void setOrderStatus(UUID id, String statusString) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invalid or nonexistent order"));

        Status status = Status.valueOf(statusString);
        order.setStatus(status);
        orderRepository.save(order);
        log.info("Message received, setting order {} status {}", order.getId(), order.getStatus().getName());
    }

    public BigDecimal totalAmount(Order order){
        return order.getItems().stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void message(Order order){
        try {
            messageRabbitMq.sendMessageNewOrder(orderMapper.toMessageDto(order));
            log.info("Message delivered!");
        } catch (RuntimeException e){
            order.setIntegrated(false);
            orderRepository.save(order);
        }
    }

}



