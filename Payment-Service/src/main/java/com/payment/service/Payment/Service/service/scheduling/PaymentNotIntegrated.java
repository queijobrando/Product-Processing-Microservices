package com.payment.service.Payment.Service.service.scheduling;

import com.payment.service.Payment.Service.dto.OrderMessageDto;
import com.payment.service.Payment.Service.mapper.PaymentMapper;
import com.payment.service.Payment.Service.model.Payment;
import com.payment.service.Payment.Service.repository.PaymentRepository;
import com.payment.service.Payment.Service.service.MessageRabbitMq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class PaymentNotIntegrated {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    public MessageRabbitMq messageRabbitMq;

    @Autowired
    public PaymentMapper paymentMapper;


    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void findNotIntegratedPayment(){
        paymentRepository.findAllByIntegratedIsFalse().forEach(payment -> {
            try {
                OrderMessageDto orderMessage = paymentMapper.toMessageDto(payment);
                messageRabbitMq.sendMessagePaymentStatus(orderMessage);
                updatePayment(payment);
            } catch (RuntimeException e){
                log.error(e.getMessage());
            }
        });
    }

    public void updatePayment(Payment payment){
        payment.setIntegrated(true);
        paymentRepository.save(payment);
    }
}
