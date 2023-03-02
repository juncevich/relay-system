package com.relay.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler
// extends ResponseEntityExceptionHandler
{

    // @ExceptionHandler(Exception.class)
    // protected ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {
    //
    // ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(),
    // request.getDescription(false));
    //
    // return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    @ExceptionHandler(RelayNotFoundException.class)
    protected ResponseEntity handleRelayNotFoundExceptions(RelayNotFoundException ex,
            WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // @Override
    // protected ResponseEntity<Object> handleMethodArgumentNotValid(
    // MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
    // WebRequest request) {
    //
    // ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(),
    // "Validation failed", ex.getBindingResult().toString());
    //
    // return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    // }
}
