package com.backend.produtosApi.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.backend.produtosApi.model.error.ErrorMessage;
import com.backend.produtosApi.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHeadler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> hendlerResponseNotFoundExcepiton(ResourceNotFoundException ex){

        ErrorMessage error = new ErrorMessage("Not found", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
}
