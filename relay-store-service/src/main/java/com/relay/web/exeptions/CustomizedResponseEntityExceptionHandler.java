package com.relay.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.relay.infrastructure.rest.exception.handler.GlobalExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends GlobalExceptionHandler {

    // @ExceptionHandler(Exception.class)
    // protected ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {
    //
    // ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(),
    // request.getDescription(false));
    //
    // return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    @ExceptionHandler
    protected ProblemDetail handleRelayNotFoundExceptions(RelayNotFoundException ex,
                                                          WebRequest request) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());


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
