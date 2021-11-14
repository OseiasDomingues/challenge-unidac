package com.grupodl.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.grupodl.services.exceptions.FieldInvalidException;
import com.grupodl.services.exceptions.ResourceAlreadyExistsException;
import com.grupodl.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    ResponseEntity<StandardError> objNotFound(ResourceAlreadyExistsException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String msgError = "O objeto já existe.";
        StandardError err = new StandardError(Instant.now(),status.value(),msgError,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<StandardError> objNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String msgError = "Objeto não encontrado";
        StandardError err = new StandardError(Instant.now(),status.value(),msgError,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(FieldInvalidException.class)
    ResponseEntity<StandardError> fieldInvalid(FieldInvalidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String msgError = "Campo Invalido!";
        StandardError err = new StandardError(Instant.now(),status.value(),msgError,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
