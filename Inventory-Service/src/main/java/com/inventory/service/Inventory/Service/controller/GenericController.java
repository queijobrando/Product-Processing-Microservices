package com.inventory.service.Inventory.Service.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface GenericController {

    default URI generateHeaderLocation(UUID id){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/product/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
