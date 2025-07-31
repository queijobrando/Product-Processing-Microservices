package com.productprocessing.orderservice.Order.Service.service;

import com.productprocessing.orderservice.Order.Service.dto.order.OrderMessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.productprocessing.orderservice.Order.Service.config.RabbitMqConfig.ORDER_CREATED_RK;
import static com.productprocessing.orderservice.Order.Service.config.RabbitMqConfig.ORDER_EXCHANGE;

@Service
public class MessageRabbitMq {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public MessageRabbitMq(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageNewOrder(OrderMessageDto message){
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_CREATED_RK, message);
    }

}
