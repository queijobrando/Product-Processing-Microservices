package com.productprocessing.orderservice.Order.Service.listener;

import com.productprocessing.orderservice.Order.Service.dto.order.OrderMessageDto;
import com.productprocessing.orderservice.Order.Service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.productprocessing.orderservice.Order.Service.config.RabbitMqConfig.*;

@Component
@Slf4j
public class QueuesListener {

    @Autowired
    public OrderService orderService;

    @RabbitListener(queues = {INVENTORY_STATUS_ORDER_QUEUE, PAYMENT_STATUS_QUEUE})
    public void handleOrderMessage(OrderMessageDto dto){
        log.info("Message received: Order:{}, status: {}", dto.id(), dto.status());
        orderService.setOrderStatus(dto.id(), dto.status());
    }


}
