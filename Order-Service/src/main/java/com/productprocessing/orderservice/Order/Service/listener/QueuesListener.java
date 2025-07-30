package com.productprocessing.orderservice.Order.Service.listener;

import com.productprocessing.orderservice.Order.Service.dto.order.OrderMessageDto;
import com.productprocessing.orderservice.Order.Service.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.productprocessing.orderservice.Order.Service.config.RabbitMqConfig.INVENTORY_RESERVED_QUEUE;

@Component
public class QueuesListener {

    @Autowired
    public OrderService orderService;

    @RabbitListener(queues = INVENTORY_RESERVED_QUEUE)
    public void inventoryReserved(OrderMessageDto dto){
        orderService.setOrderStatus(dto.id(), dto.status());
    }

}
