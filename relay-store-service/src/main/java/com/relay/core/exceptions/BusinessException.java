package com.relay.core.exceptions;

/**
 * Base exception class for all business logic exceptions in the application.
 * Provides a consistent exception hierarchy for domain-specific errors.
 */
public abstract class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
