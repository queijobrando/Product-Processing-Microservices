package com.productprocessing.orderservice.Order.Service.listener;

import com.productprocessing.orderservice.Order.Service.dto.order.OrderMessageDto;
import com.productprocessing.orderservice.Order.Service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.productprocessing.orderservice.Order.Service.config.RabbitMqConfig.INVENTORY_FAILED_QUEUE;
import static com.productprocessing.orderservice.Order.Service.config.RabbitMqConfig.INVENTORY_RESERVED_QUEUE;

@Component
@Slf4j
public class QueuesListener {

    @Autowired
    public OrderService orderService;

    @RabbitListener(queues = {INVENTORY_RESERVED_QUEUE, INVENTORY_FAILED_QUEUE})
    public void handleOrderMessage(OrderMessageDto dto){
        orderService.setOrderStatus(dto.id(), dto.status());
    }


}
