package com.relay.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.relay.infrastructure.rest.exception.handler.GlobalExceptionHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler that handles application-specific exceptions
 * and returns RFC 7807 ProblemDetail responses.
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends GlobalExceptionHandler {

    /**
     * Handles entity not found exceptions.
     * Returns HTTP 404 with details about which entity was not found.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ProblemDetail handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problemDetail.setTitle("Entity Not Found");
        problemDetail.setType(URI.create("https://api.relay-system.com/problems/entity-not-found"));

        if (ex.getEntityType() != null && ex.getIdentifier() != null) {
            problemDetail.setProperty("entityType", ex.getEntityType());
            problemDetail.setProperty("identifier", ex.getIdentifier());
        }

        return problemDetail;
    }

    /**
     * Handles validation errors from @Valid annotated request bodies.
     * Returns HTTP 400 with field-level validation error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Validation failed for one or more fields"
        );
        problemDetail.setTitle("Validation Error");
        problemDetail.setType(URI.create("https://api.relay-system.com/problems/validation-error"));
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    /**
     * Handles illegal argument exceptions from business logic.
     * Returns HTTP 400 with error details.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ProblemDetail handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        problemDetail.setTitle("Invalid Argument");
        problemDetail.setType(URI.create("https://api.relay-system.com/problems/invalid-argument"));

        return problemDetail;
    }

    /**
     * Handles invalid business state exceptions.
     * Returns HTTP 400 with business rule violation details.
     */
    @ExceptionHandler(InvalidBusinessStateException.class)
    protected ProblemDetail handleInvalidBusinessState(InvalidBusinessStateException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        problemDetail.setTitle("Business Rule Violation");
        problemDetail.setType(URI.create("https://api.relay-system.com/problems/business-rule-violation"));

        return problemDetail;
    }
}
