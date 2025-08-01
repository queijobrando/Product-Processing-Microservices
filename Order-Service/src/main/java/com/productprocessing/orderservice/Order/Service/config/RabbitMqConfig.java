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

    public static final String INVENTORY_STATUS_ORDER_QUEUE = "inventory-status-order.ms";
    public static final String INVENTORY_STATUS_PAYMENT_QUEUE = "inventory-status-payment.ms";
    public static final String PAYMENT_STATUS_QUEUE = "payment-status.ms";
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_CREATED_RK = "order-created";
    public static final String INVENTORY_STATUS_RK = "inventory-status";
    public static final String PAYMENT_STATUS_RK = "payment-status";
    public static final String ORDER_CREATED_QUEUE = "order-created.ms";

    // Queues
    @Bean
    public Queue createOrderCreatedQueue(){
        return new Queue(ORDER_CREATED_QUEUE);
    }

    @Bean
    public Queue createInventoryStatusOrderQueue(){
        return new Queue(INVENTORY_STATUS_ORDER_QUEUE);
    }

    @Bean
    public Queue createInventoryStatusPaymentQueue(){
        return new Queue(INVENTORY_STATUS_PAYMENT_QUEUE);
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

    //Binds
    @Bean
    public Binding createOrderCreatedBind(){
        return BindingBuilder
                .bind(createOrderCreatedQueue())
                .to(createOrderExchange())
                .with(ORDER_CREATED_RK);
    }

    @Bean
    public Binding createInventoryStatusOrderBind(){
        return BindingBuilder
                .bind(createInventoryStatusOrderQueue())
                .to(createOrderExchange())
                .with(INVENTORY_STATUS_RK);
    }

    @Bean
    public Binding createInventoryStatusPaymentBind(){
        return BindingBuilder
                .bind(createInventoryStatusPaymentQueue())
                .to(createOrderExchange())
                .with(INVENTORY_STATUS_RK);
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
