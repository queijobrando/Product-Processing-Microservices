package com.inventory.service.Inventory.Service.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface GenericController {
    default URI generateHeaderLocation(UUID id){
        return ServletUriComponentsBuilder
                .fromPath("http://localhost:8082/product/{id}")
                .buildAndExpand()
                .toUri();
    }
}
