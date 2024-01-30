package com.cydeo.lab8restecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationError { // i wll use my ExceptionWrapper class

    private String errorField;// which field is wrong
    private Object rejectedValue;// which value is rejected
    private String reason;// and what is the reason
}
