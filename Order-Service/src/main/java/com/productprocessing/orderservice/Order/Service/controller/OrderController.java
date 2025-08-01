package com.productprocessing.orderservice.Order.Service.controller;

import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderRequestDto;
import com.productprocessing.orderservice.Order.Service.dto.neworder.OrderResponseDto;
import com.productprocessing.orderservice.Order.Service.dto.order.OrderResponseListDto;
import com.productprocessing.orderservice.Order.Service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

    @Operation(
            summary = "Novo pedido",
            description = "Gerar um novo pedido",
            tags = "Pedidos",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Novo",
                                            value = """
                        {
                           "customerName": "Nome Cliente",
                           "items": [
                             {
                               "productId": "b3f5e9c7-16ac-4a60-84b2-238c3e17e92e",
                               "name": "Teclado Mecânico",
                               "quantity": 1,
                               "price": 350.00
                             },
                             {
                               "productId": "2a9c1f32-46d7-41b4-a7a3-5c58a0ad0be1",
                               "name": "Mouse Gamer",
                               "quantity": 2,
                               "price": 150.00
                             }
                           ]
                         }
                        """
                                    )}))
    )
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
