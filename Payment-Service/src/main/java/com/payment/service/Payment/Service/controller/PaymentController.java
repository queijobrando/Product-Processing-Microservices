package com.payment.service.Payment.Service.controller;

import com.payment.service.Payment.Service.dto.payment.PaymentInfoDto;
import com.payment.service.Payment.Service.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "Informações do Pagamento",
            description = "Retorna informações do pagamento",
            tags = "Pagamentos")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentInfoDto> getPaymentInfo(@PathVariable UUID id){
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @Operation(
            summary = "Todos os Pagamentos",
            description = "Retorna todos os pagamentos registrados",
            tags = "Pagamentos")
    @GetMapping
    public ResponseEntity<List<PaymentInfoDto>> getAllPayments(){
        List<PaymentInfoDto> payments = paymentService.getAllPayments();

        if (payments.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(payments);
    }

}
