package com.payment.service.Payment.Service.service;

import com.payment.service.Payment.Service.dto.OrderMessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.payment.service.Payment.Service.config.RabbitMqConfig.ORDER_EXCHANGE;
import static com.payment.service.Payment.Service.config.RabbitMqConfig.PAYMENT_STATUS_RK;

@Service
public class MessageRabbitMq {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public MessageRabbitMq(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessagePaymentStatus(OrderMessageDto message){
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, PAYMENT_STATUS_RK, message);
    }

}
