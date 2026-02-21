package com.relay.web.exceptions;

/**
 * @deprecated Use {@link com.relay.core.exceptions.InvalidBusinessStateException} instead.
 */
@Deprecated(forRemoval = true)
public class InvalidBusinessStateException extends com.relay.core.exceptions.InvalidBusinessStateException {

    public InvalidBusinessStateException(String message) {
        super(message);
    }

    public InvalidBusinessStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
