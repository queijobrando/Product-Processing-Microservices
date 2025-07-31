package com.inventory.service.Inventory.Service.service;

import com.inventory.service.Inventory.Service.dto.order.OrderMessageDto;
import com.inventory.service.Inventory.Service.dto.order.OrderMessageItems;
import com.inventory.service.Inventory.Service.dto.product.ProductInfoDto;
import com.inventory.service.Inventory.Service.mapper.ProductMapper;
import com.inventory.service.Inventory.Service.model.Product;
import com.inventory.service.Inventory.Service.model.enun.Status;
import com.inventory.service.Inventory.Service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.inventory.service.Inventory.Service.config.RabbitMqConfig.INVENTORY_FAILED_RK;
import static com.inventory.service.Inventory.Service.config.RabbitMqConfig.INVENTORY_RESERVED_RK;

@Service
@Slf4j
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public ProductMapper productMapper;

    @Autowired
    public MessageRabbitMq messageRabbitMq;

    public List<ProductInfoDto> getAllProducts(){
       return productRepository.findAll()
               .stream()
               .map(productMapper::toDto)
               .toList();
    }

    @Transactional
    public void inventoryCheck(OrderMessageDto dto) {
        List<OrderMessageItems> items = dto.items();

        if (items.isEmpty()) {
            log.warn("Order {} has no items", dto.id());
            sendMessage(dto, Status.INVENTORY_FAILED.getName(), INVENTORY_FAILED_RK);
            return;
        }

        for (OrderMessageItems item : items) {
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Invalid or nonexistent product"));

            if (item.quantity() > product.getQuantity()) {
                log.error("Product quantity higher than stock");
                sendMessage(dto, Status.INVENTORY_FAILED.getName(), INVENTORY_FAILED_RK);
                return;
            }

            product.setQuantity(product.getQuantity() - item.quantity());
        }

        sendMessage(dto, Status.INVENTORY_RESERVED.getName(), INVENTORY_RESERVED_RK);
    }

    public void sendMessage(OrderMessageDto dto, String status, String routingKey){
        OrderMessageDto orderMessage = new OrderMessageDto(
                dto.id(), status, dto.items()
        );
        messageRabbitMq.sendMessageOrderStatus(orderMessage, routingKey);
        log.info("Sending order {} with status {}", dto.id(), status);
    }
}
