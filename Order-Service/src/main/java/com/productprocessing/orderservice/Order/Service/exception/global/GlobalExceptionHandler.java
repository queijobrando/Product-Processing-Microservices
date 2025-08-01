package com.productprocessing.orderservice.Order.Service.exception.global;

import com.productprocessing.orderservice.Order.Service.dto.exception.ErrorAnswer;
import com.productprocessing.orderservice.Order.Service.dto.exception.ErrorField;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorAnswer handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorField> errorsList = fieldErrors
                .stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorAnswer(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error",
                errorsList
        );
    }

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
