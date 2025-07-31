package com.inventory.service.Inventory.Service.listener;

import com.inventory.service.Inventory.Service.dto.order.OrderMessageDto;
import com.inventory.service.Inventory.Service.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.inventory.service.Inventory.Service.config.RabbitMqConfig.ORDER_CREATED_QUEUE;


@Component
public class QueuesListener {

    @Autowired
    public ProductService productService;

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void orderCreated(OrderMessageDto dto){
        productService.inventoryCheck(dto);
    }

}
