package com.payment.service.Payment.Service.service;

import com.payment.service.Payment.Service.dto.OrderMessageDto;
import com.payment.service.Payment.Service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    public PaymentRepository paymentRepository;

    public void paymentProcess(OrderMessageDto dto){

    }

}
