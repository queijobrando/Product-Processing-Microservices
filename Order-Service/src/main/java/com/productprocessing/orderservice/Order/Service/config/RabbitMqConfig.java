package com.productprocessing.orderservice.Order.Service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String INVENTORY_RESERVED_QUEUE = "inventory-reserved.ms";
    public static final String INVENTORY_FAILED_QUEUE = "inventory-failed.ms";
    public static final String PAYMENT_STATUS_QUEUE = "payment-status.ms";
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_CREATED_RK = "order-created";
    public static final String INVENTORY_RESERVED_RK = "inventory-reserved";
    public static final String INVENTORY_FAILED_RK = "inventory-failed";
    public static final String PAYMENT_STATUS_RK = "payment-status";
    public static final String ORDER_CREATED_QUEUE = "order-created.ms";

    // Queues
    @Bean
    public Queue createOrderCreatedQueue(){
        return new Queue(ORDER_CREATED_QUEUE);
    }

    @Bean
    public Queue createInventoryReservedQueue(){
        return new Queue(INVENTORY_RESERVED_QUEUE);
    }

    @Bean
    public Queue createInventoryFailedQueue(){
        return new Queue(INVENTORY_FAILED_QUEUE);
    }

    @Bean
    public Queue createPaymentStatusQueue(){
        return new Queue(PAYMENT_STATUS_QUEUE);
    }

    //Exchange
    @Bean
    public DirectExchange createOrderExchange(){
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE).build();
    }

    @Bean
    public Binding createOrderCreatedBind(){
        return BindingBuilder
                .bind(createOrderCreatedQueue())
                .to(createOrderExchange())
                .with(ORDER_CREATED_RK);
    }

    //Binds
    @Bean
    public Binding createInventoryReservedBind(){
        return BindingBuilder
                .bind(createInventoryReservedQueue())
                .to(createOrderExchange())
                .with(INVENTORY_RESERVED_RK);
    }

    @Bean
    public Binding createInventoryFailedBind(){
        return BindingBuilder
                .bind(createInventoryFailedQueue())
                .to(createOrderExchange())
                .with(INVENTORY_FAILED_RK);
    }

    @Bean
    public Binding createPaymentStatusBind(){
        return BindingBuilder
                .bind(createPaymentStatusQueue())
                .to(createOrderExchange())
                .with(PAYMENT_STATUS_RK);
    }

    // ADMIN para criar filas exchanges...
    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    //Message converter
    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }


}
