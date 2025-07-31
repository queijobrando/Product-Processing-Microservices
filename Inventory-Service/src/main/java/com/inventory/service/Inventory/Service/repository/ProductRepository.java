package com.inventory.service.Inventory.Service.repository;

import com.inventory.service.Inventory.Service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
