package com.cydeo.lab8restecommerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice   // central place to manage all other custom exceptions
public class GlobalExceptionHandler {
// custom exception and instead of Response wrapper to return
// I will create my own wrapper

    @ExceptionHandler(NotFoundException.class) // as parameter capturing my notFoundException
    public ResponseEntity<ExceptionWrapper> processNotFoundException(NotFoundException ex) {
        //create a json body and return it
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(ex.getMessage(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);//NOT_FOUND 404

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)//as parameter capturing my MethodArgumentNotValidException
    public ResponseEntity<ExceptionWrapper> processValidationError(MethodArgumentNotValidException ex) {
        return handleMethodArgumentNotValid(ex);
    }

    protected ResponseEntity<ExceptionWrapper> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper("Invalid Input(s)", HttpStatus.BAD_REQUEST);
        List<ValidationError> validationErrors = new ArrayList<>();// here is the empty list

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {  // loop to accessing all errors
            //capture this 3 field
            String fieldName = ((FieldError) error).getField();// here needs cast
            Object rejectedValue = ((FieldError) error).getRejectedValue();// here needs cast
            String errorMessage = error.getDefaultMessage();
            // and save in validateError message
            ValidationError validationError = new ValidationError(fieldName, rejectedValue, errorMessage);
            validationErrors.add(validationError); // and added to the list
        }
        //to complete my list i set validationErrorList
        exceptionWrapper.setValidationErrorList(validationErrors);
        exceptionWrapper.setErrorCount(validationErrors.size());// how many errors we have it will show how many errors in the list
        return new ResponseEntity<>(exceptionWrapper, HttpStatus.BAD_REQUEST);
    }

    // this method is for all other potential exception
    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    public ResponseEntity<ExceptionWrapper> genericExceptionHandler() {
        return new ResponseEntity<>(new ExceptionWrapper("Action failed: An error occurred!", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CurrencyTypeNotFoundException.class)
    // as a variable i will accept NotFoundException as parameter here
    public ResponseEntity<ExceptionWrapper> handleCurrencyTypeNotFoundException(CurrencyTypeNotFoundException ex) {
        // create a JSON body and return it
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(ex.getMessage(), HttpStatus.NOT_FOUND);
        exceptionWrapper.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);


    }


}

