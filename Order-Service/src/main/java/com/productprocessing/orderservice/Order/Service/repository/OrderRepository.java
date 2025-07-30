package com.productprocessing.orderservice.Order.Service.repository;

import com.productprocessing.orderservice.Order.Service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByIntegratedIsFalse();
}
