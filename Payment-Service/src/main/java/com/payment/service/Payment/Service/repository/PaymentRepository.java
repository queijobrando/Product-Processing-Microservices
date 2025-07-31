package com.payment.service.Payment.Service.repository;

import com.payment.service.Payment.Service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
