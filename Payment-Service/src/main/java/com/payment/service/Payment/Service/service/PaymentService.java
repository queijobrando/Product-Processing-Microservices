package com.payment.service.Payment.Service.service;

import com.payment.service.Payment.Service.dto.OrderMessageDto;
import com.payment.service.Payment.Service.dto.payment.PaymentInfoDto;
import com.payment.service.Payment.Service.mapper.PaymentMapper;
import com.payment.service.Payment.Service.model.Payment;
import com.payment.service.Payment.Service.model.enun.Status;
import com.payment.service.Payment.Service.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    public PaymentRepository paymentRepository;

    @Autowired
    public PaymentMapper paymentMapper;

    @Autowired
    public MessageRabbitMq messageRabbitMq;

    public void paymentProcess(OrderMessageDto dto){
        log.info("Processing payment...");
        try {
            Thread.sleep(3000);

            Status status = Math.random() > 0.2 ? Status.PAYMENT_SUCCEEDED : Status.PAYMENT_FAILED;
            Payment payment = paymentMapper.toEntity(dto);
            payment.setStatus(status);
            paymentRepository.save(payment);
            log.info("Payment {} for order {}", status.name(), dto.id());

            OrderMessageDto order = new OrderMessageDto(dto.id(), status.getName(), dto.totalAmount(), dto.items());
            message(order, payment);

        } catch (InterruptedException e) {
            log.error("Payment processing error", e);
            Thread.currentThread().interrupt();
        }
    }

    public PaymentInfoDto getPayment(UUID id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment info invalid or nonexistent"));

        return paymentMapper.toDto(payment);
    }

    public List<PaymentInfoDto> getAllPayments(){
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toDto)
                .toList();
    }


    public void message(OrderMessageDto dto, Payment payment){
        try {
            messageRabbitMq.sendMessagePaymentStatus(dto);
            log.info("Payment message delivered!");
        } catch (RuntimeException e){
            log.error(e.getMessage());
            payment.setIntegrated(false);
            paymentRepository.save(payment);
        }
    }

}
