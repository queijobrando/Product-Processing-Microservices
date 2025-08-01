package com.inventory.service.Inventory.Service.mapper;

import com.inventory.service.Inventory.Service.dto.product.ProductInfoDto;
import com.inventory.service.Inventory.Service.dto.product.ProductRequestDto;
import com.inventory.service.Inventory.Service.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductInfoDto toDto(Product product);

    Product toEntity(ProductRequestDto dto);

}
