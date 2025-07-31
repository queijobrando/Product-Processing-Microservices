package com.inventory.service.Inventory.Service.service;

import com.inventory.service.Inventory.Service.dto.order.OrderMessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.inventory.service.Inventory.Service.config.RabbitMqConfig.ORDER_EXCHANGE;

@Service
public class MessageRabbitMq {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public MessageRabbitMq(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageOrderStatus(OrderMessageDto message, String routingKey){
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, routingKey, message);
    }

}
