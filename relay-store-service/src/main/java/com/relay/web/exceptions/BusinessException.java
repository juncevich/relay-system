package com.relay.web.exceptions;

/**
 * @deprecated Use {@link com.relay.core.exceptions.BusinessException} instead.
 */
@Deprecated(forRemoval = true)
public abstract class BusinessException extends com.relay.core.exceptions.BusinessException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
