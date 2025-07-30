package com.productprocessing.orderservice.Order.Service.controller;

import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderRequestDto;
import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderResponseDto;
import com.productprocessing.orderservice.Order.Service.dto.order.OrderResponseListDto;
import com.productprocessing.orderservice.Order.Service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController implements GenericController {

    @Autowired
    public OrderService orderService;

    @PostMapping("/new")
    public ResponseEntity<OrderResponseDto> newOrder(@Valid @RequestBody OrderRequestDto dto){
        OrderResponseDto order = orderService.newOrder(dto);

        URI location = generateHeaderLocation(order.id());

        return ResponseEntity.created(location).body(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderInfo(@PathVariable UUID id){
        OrderResponseDto order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseListDto>> getAllOrders(){
        List<OrderResponseListDto> orders = orderService.getAllOrders();

        if (orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orders);
    }

}
