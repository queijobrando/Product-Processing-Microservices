package com.inventory.service.Inventory.Service.controller;

import com.inventory.service.Inventory.Service.dto.product.ProductInfoDto;
import com.inventory.service.Inventory.Service.dto.product.ProductRequestDto;
import com.inventory.service.Inventory.Service.service.ProductService;
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
@RequestMapping("/product")
public class ProductController implements GenericController {

    @Autowired
    public ProductService productService;

    @Operation(
            summary = "Novo Produto",
            description = "Endpoint para gerar um novo Produto",
            tags = "Produtos",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Novo",
                                            value = """
                        {
                          "name": "Teclado Mecânico",
                          "price": 249.90,
                          "quantity": 10
                        }
                        """
                                    )}))
    )
    @PostMapping
    public ResponseEntity<ProductInfoDto> newProduct(@Valid @RequestBody ProductRequestDto dto){
        ProductInfoDto product = productService.newProduct(dto);

        URI location = generateHeaderLocation(product.id());

        return ResponseEntity.created(location).body(product);
    }

    @Operation(
            summary = "Informações do Produto",
            description = "Retorna informações do produto",
            tags = "Produtos")
    @GetMapping("/{id}")
    public ResponseEntity<ProductInfoDto> getProductInfo(@PathVariable UUID id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @Operation(
            summary = "Todos os Produtos",
            description = "Retorna todos os produtos registrados",
            tags = "Produtos")
    @GetMapping
    public ResponseEntity<List<ProductInfoDto>> getAllProducts(){
        List<ProductInfoDto> products = productService.getAllProducts();

        if (products.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

}
