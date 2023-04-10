package com.cydeo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
public class ResponseWrapper {//we are customized JSON output with responseWrapper class
    //we will see in API
    private boolean success;
    private String message;
    private Integer code;
    private Object data; // can be any dto

    public ResponseWrapper(String message, Object data){
        this.message=message;
        this.data=data;
        this.code= HttpStatus.OK.value();
        this.success=true;

    }
    public ResponseWrapper(String message){//if we delete something we can't see data thats why we create two cunstructors
        this.message=message;
        this.code=HttpStatus.OK.value();
        this.success=true;
    }




}
