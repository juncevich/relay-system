package com.relay.web.exceptions;

/**
 * Exception thrown when a storage entity (Warehouse, Stand, or RelayCabinet) is not found.
 */
public class StorageNotFoundException extends EntityNotFoundException {

    public StorageNotFoundException(Long id) {
        super("Storage", id);
    }

    public StorageNotFoundException(String message) {
        super(message);
    }
}
