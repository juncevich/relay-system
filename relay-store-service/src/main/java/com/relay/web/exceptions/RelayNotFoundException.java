package com.relay.web.exceptions;

/**
 * Exception thrown when a relay entity is not found.
 */
public class RelayNotFoundException extends EntityNotFoundException {

    public RelayNotFoundException(Long id) {
        super("Relay", id);
    }

    public RelayNotFoundException(String message) {
        super(message);
    }
}
