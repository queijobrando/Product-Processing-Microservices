package com.inventory.service.Inventory.Service.controller;

import com.inventory.service.Inventory.Service.dto.product.ProductInfoDto;
import com.inventory.service.Inventory.Service.dto.product.ProductRequestDto;
import com.inventory.service.Inventory.Service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController implements GenericController {

    @Autowired
    public ProductService productService;

    @PostMapping
    public ResponseEntity<ProductInfoDto> newProduct(@Valid @RequestBody ProductRequestDto dto){
        ProductInfoDto product = productService.newProduct(dto);

        URI location = generateHeaderLocation(product.id());

        return ResponseEntity.created(location).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInfoDto> getProductInfo(@PathVariable UUID id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductInfoDto>> getAllProducts(){
        List<ProductInfoDto> products = productService.getAllProducts();

        if (products.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

}
