package com.relay.web.exceptions;

/**
 * @deprecated Use {@link com.relay.core.exceptions.ShelfNotFoundException} instead.
 */
@Deprecated(forRemoval = true)
public class ShelfNotFoundException extends com.relay.core.exceptions.ShelfNotFoundException {

    public ShelfNotFoundException(Long id) {
        super(id);
    }

    public ShelfNotFoundException(String message) {
        super(message);
    }
}
