package com.productprocessing.orderservice.Order.Service.service.scheduling;

import com.productprocessing.orderservice.Order.Service.dto.order.OrderMessageDto;
import com.productprocessing.orderservice.Order.Service.mapper.OrderMapper;
import com.productprocessing.orderservice.Order.Service.model.Order;
import com.productprocessing.orderservice.Order.Service.repository.OrderRepository;
import com.productprocessing.orderservice.Order.Service.service.MessageRabbitMq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class OrderNotIntegrated {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderMapper orderMapper;

    @Autowired
    public MessageRabbitMq messageRabbitMq;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void findNotIntegratedOrder(){
        orderRepository.findAllByIntegratedIsFalse().forEach(order -> {
            try {
                OrderMessageDto orderMessage = orderMapper.toMessageDto(order);
                messageRabbitMq.sendMessageNewOrder(orderMessage);
                updateOrder(order);
            } catch (RuntimeException e){
                log.error(e.getMessage());
            }
        });
    }

    public void updateOrder(Order order){
        order.setIntegrated(true);
        orderRepository.save(order);
    }

}
