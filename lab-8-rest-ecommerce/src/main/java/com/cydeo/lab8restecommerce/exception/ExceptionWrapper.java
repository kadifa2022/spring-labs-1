package com.cydeo.lab8restecommerce.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ExceptionWrapper { // this is my costume exception wrapper

    //fields I want to be in my exception wrapper
    public String message;
    public HttpStatus httpStatus;


    //constructor
    public ExceptionWrapper(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus =httpStatus;

    }

}
