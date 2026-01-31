package com.relay.web.exceptions;

/**
 * Exception thrown when a shelf entity is not found.
 */
public class ShelfNotFoundException extends EntityNotFoundException {

    public ShelfNotFoundException(Long id) {
        super("Shelf", id);
    }

    public ShelfNotFoundException(String message) {
        super(message);
    }
}
