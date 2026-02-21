package com.relay.web.exceptions;

/**
 * @deprecated Use {@link com.relay.core.exceptions.RelayNotFoundException} instead.
 */
@Deprecated(forRemoval = true)
public class RelayNotFoundException extends com.relay.core.exceptions.RelayNotFoundException {

    public RelayNotFoundException(Long id) {
        super(id);
    }

    public RelayNotFoundException(String message) {
        super(message);
    }
}
