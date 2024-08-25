package com.example.productservice.Exceptions;

import com.example.productservice.DTOS.ExceptionHandlerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {


    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException e) {
        ExceptionHandlerDTO handlerDTO = new ExceptionHandlerDTO(e.getMessage(),404);

        return  new  ResponseEntity<>(handlerDTO, HttpStatus.NOT_FOUND);
    }

}
