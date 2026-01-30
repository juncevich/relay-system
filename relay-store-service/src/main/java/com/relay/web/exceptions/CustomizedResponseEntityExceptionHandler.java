package com.relay.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.relay.infrastructure.rest.exception.handler.GlobalExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler
    protected ProblemDetail handleRelayNotFoundExceptions(RelayNotFoundException ex,
                                                          WebRequest request) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
