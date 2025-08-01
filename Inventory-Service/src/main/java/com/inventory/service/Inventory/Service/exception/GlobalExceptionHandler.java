package com.inventory.service.Inventory.Service.exception;

import com.inventory.service.Inventory.Service.dto.exception.ErrorAnswer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorAnswer handleMethodArgumentNotFoundException(EntityNotFoundException e){
        return ErrorAnswer.notFound(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorAnswer handleInternalServerErrors(RuntimeException e){
        System.out.println(e.getMessage());
        return new ErrorAnswer(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error. Contact API support",
                List.of()
        );
    }


}
