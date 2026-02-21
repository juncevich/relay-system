package com.relay.web.exceptions;

/**
 * @deprecated Use {@link com.relay.core.exceptions.StorageNotFoundException} instead.
 */
@Deprecated(forRemoval = true)
public class StorageNotFoundException extends com.relay.core.exceptions.StorageNotFoundException {

    public StorageNotFoundException(Long id) {
        super(id);
    }

    public StorageNotFoundException(String message) {
        super(message);
    }
}
