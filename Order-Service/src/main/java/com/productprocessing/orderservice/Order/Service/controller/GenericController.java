package com.productprocessing.orderservice.Order.Service.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface GenericController {
    default URI generateHeaderLocation(UUID id){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/order/{id}")
                .buildAndExpand(id)
                .toUri();
    }


}
