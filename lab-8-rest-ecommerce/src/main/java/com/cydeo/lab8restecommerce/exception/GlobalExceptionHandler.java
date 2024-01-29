package com.cydeo.lab8restecommerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // central place to manage all other custom exceptions
public class GlobalExceptionHandler {
// custom exception and instead of Response wrapper to return
// I will create my own wrapper

    @ExceptionHandler(NotFoundException.class) // as parameter capturing my notFoundException
    public ResponseEntity<ExceptionWrapper> processNotFoundException(NotFoundException ex){
        //create a json body and return it
      ExceptionWrapper exceptionWrapper = new ExceptionWrapper(ex.getMessage(),HttpStatus.NOT_FOUND);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);//NOT_FOUND 404


    }

}
