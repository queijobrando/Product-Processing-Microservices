package com.inventory.service.Inventory.Service.listener;

import com.inventory.service.Inventory.Service.dto.order.OrderMessageDto;
import com.inventory.service.Inventory.Service.model.enun.Status;
import com.inventory.service.Inventory.Service.service.MessageRabbitMq;
import com.inventory.service.Inventory.Service.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.inventory.service.Inventory.Service.config.RabbitMqConfig.ORDER_CREATED_QUEUE;


@Component
@Slf4j
public class QueuesListener {

    @Autowired
    public ProductService productService;

    @Autowired
    public MessageRabbitMq messageRabbitMq;

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void orderCreated(OrderMessageDto dto){
        try {
            productService.inventoryCheck(dto);
            messageRabbitMq.sendMessageOrderStatus(new OrderMessageDto(
                    dto.id(), Status.PROCESSING_PAYMENT.getName(), dto.totalAmount(), dto.items()
            ));
            log.info("Order {} is processing payment", dto.id());

        } catch (EntityNotFoundException | IllegalArgumentException e){
            log.error("Order {} failed: {}", dto.id(), e.getMessage());
            messageRabbitMq.sendMessageOrderStatus(new OrderMessageDto(
                    dto.id(), Status.FAILED.getName(), dto.totalAmount(), dto.items()
            ));
        } catch (IllegalStateException e){
            log.error("Order {} out of stock: {}", dto.id(), e.getMessage());
            messageRabbitMq.sendMessageOrderStatus(new OrderMessageDto(
                    dto.id(), Status.OUT_OF_STOCK.getName(), dto.totalAmount(), dto.items()
            ));
        }
    }

}
