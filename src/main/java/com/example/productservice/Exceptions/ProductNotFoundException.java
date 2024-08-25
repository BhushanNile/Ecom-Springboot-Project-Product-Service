package com.example.productservice.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String s) {
    }

    public ProductNotFoundException() {
    }
}
