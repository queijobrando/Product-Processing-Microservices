package com.productprocessing.orderservice.Order.Service.dto.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorAnswer(int status, String mensagem, List<ErrorField> erroCampos) {

    public static ErrorAnswer notFound(String mensagem){
        return new ErrorAnswer(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }
}
