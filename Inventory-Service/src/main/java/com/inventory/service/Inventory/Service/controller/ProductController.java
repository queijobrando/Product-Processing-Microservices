package com.inventory.service.Inventory.Service.controller;

import com.inventory.service.Inventory.Service.dto.product.ProductInfoDto;
import com.inventory.service.Inventory.Service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductInfoDto>> getAllProducts(){
        List<ProductInfoDto> products = productService.getAllProducts();

        if (products.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

}
