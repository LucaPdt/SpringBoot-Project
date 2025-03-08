package com.lucapdt.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

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

    @ExceptionHandler(RoleNameNotFoundException.class)
    public ResponseEntity<HandlerResponse> handleRoleNameNotFoundException(RoleNameNotFoundException ex) {
        HandlerResponse response = new HandlerResponse();

        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return new ResponseEntity<HandlerResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HandlerResponse> handleBadCredentialsException(BadCredentialsException ex) {
        HandlerResponse response = new HandlerResponse();

        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return new ResponseEntity<HandlerResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HandlerResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HandlerResponse response = new HandlerResponse();

        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return new ResponseEntity<HandlerResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<HandlerResponse> handleAccessDeniedException(AccessDeniedException ex) {
        HandlerResponse response = new HandlerResponse();

        response.setStatusCode(HttpStatus.FORBIDDEN.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return new ResponseEntity<HandlerResponse>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<HandlerResponse> handleAuthenticationException(AuthenticationServiceException ex) {
        HandlerResponse response = new HandlerResponse();

        response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return new ResponseEntity<HandlerResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HandlerResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        HandlerResponse response = new HandlerResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Invalid data format: " + ex.getMessage());
        response.setTimestamp(new Date());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<HandlerResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        HandlerResponse response = new HandlerResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Invalid data format: " + ex.getMessage());
        response.setTimestamp(new Date());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HandlerResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        HandlerResponse response = new HandlerResponse();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("Endpoint non trovato" + ex.getMessage());
        response.setTimestamp(new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<HandlerResponse> handleAuthenticationException(AuthenticationCredentialsNotFoundException ex) {
        HandlerResponse errorResponse = new HandlerResponse();
        errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage("Token JWT mancante o non valido");
        errorResponse.setTimestamp(new Date());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}

