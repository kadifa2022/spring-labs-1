package com.cydeo.lab8restecommerce.exception;

public class CurrencyTypeNotFoundException extends RuntimeException {
    public CurrencyTypeNotFoundException(String message) {
        super(message);
    }
}
