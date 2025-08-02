package com.payment.service.Payment.Service.controller;

import com.payment.service.Payment.Service.dto.payment.PaymentInfoDto;
import com.payment.service.Payment.Service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    public PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentInfoDto> getPaymentInfo(@PathVariable UUID id){
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentInfoDto>> getAllPayments(){
        List<PaymentInfoDto> payments = paymentService.getAllPayments();

        if (payments.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(payments);
    }

}
