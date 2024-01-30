package com.cydeo.lab8restecommerce.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)// If any field is null don't send them back
public class ExceptionWrapper { // this is my costume exception wrapper

    //fields I want to be in my exception wrapper
    public String message;
    public HttpStatus httpStatus;

    private Integer errorCount;
    private List<ValidationError> validationErrorList; // this is my class


    //constructor
    public ExceptionWrapper(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus =httpStatus;

    }

}
