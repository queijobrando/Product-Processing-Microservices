package com.productprocessing.orderservice.Order.Service.dto.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorAnswer(int status, String message, List<ErrorField> fieldErros) {

    public static ErrorAnswer notFound(String message){
        return new ErrorAnswer(HttpStatus.NOT_FOUND.value(), message, List.of());
    }
}
