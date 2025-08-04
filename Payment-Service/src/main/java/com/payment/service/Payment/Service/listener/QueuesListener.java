package com.payment.service.Payment.Service.listener;

import com.payment.service.Payment.Service.dto.OrderMessageDto;
import com.payment.service.Payment.Service.model.enun.Status;
import com.payment.service.Payment.Service.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.payment.service.Payment.Service.config.RabbitMqConfig.INVENTORY_STATUS_PAYMENT_QUEUE;


@Component
@Slf4j
public class QueuesListener {

    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = INVENTORY_STATUS_PAYMENT_QUEUE)
    public void inventoryStatus(OrderMessageDto dto){
        log.info("Message received: Order:{}, status: {}", dto.id(), dto.status());

        if (Objects.equals(dto.status(), Status.OUT_OF_STOCK.name()) || Objects.equals(dto.status(), Status.FAILED.name()) ){
            log.error("Ignoring order: {}, status: {}", dto.id(), dto.status());
            return;
        }

        paymentService.paymentProcess(dto);
    }


}
