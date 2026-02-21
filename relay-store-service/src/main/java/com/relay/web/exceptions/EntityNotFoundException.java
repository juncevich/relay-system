package com.relay.web.exceptions;

/**
 * @deprecated Use {@link com.relay.core.exceptions.EntityNotFoundException} instead.
 */
@Deprecated(forRemoval = true)
public class EntityNotFoundException extends com.relay.core.exceptions.EntityNotFoundException {

    public EntityNotFoundException(String entityType, Object identifier) {
        super(entityType, identifier);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
