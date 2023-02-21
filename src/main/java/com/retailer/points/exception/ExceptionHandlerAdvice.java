package com.retailer.points.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        String errorMessage = e.getMessage();
        errorResponse.setErrorCode("400");
        errorResponse.setMessage(errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePurchaseNotFoundException(PurchaseNotFoundException e){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setErrorCode("400");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
