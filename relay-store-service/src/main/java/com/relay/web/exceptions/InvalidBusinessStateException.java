package com.relay.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a business operation violates business rules or constraints.
 * Returns HTTP 400 status code when thrown from controllers.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBusinessStateException extends BusinessException {

    public InvalidBusinessStateException(String message) {
        super(message);
    }

    public InvalidBusinessStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
