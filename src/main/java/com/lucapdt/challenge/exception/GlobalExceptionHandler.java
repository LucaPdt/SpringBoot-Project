package com.lucapdt.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.logging.Handler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AutomobileNotFoundException.class)
    public ResponseEntity<HandlerResponse> handleAutomobileNotFoundException(AutomobileNotFoundException ex) {
        HandlerResponse response = new HandlerResponse();

        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return new ResponseEntity<HandlerResponse>(response, HttpStatus.NOT_FOUND);
    }
}

