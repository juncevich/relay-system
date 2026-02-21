package com.relay.core.exceptions;

/**
 * Exception thrown when a location entity (Station, TrackPoint, or Crossing) is not found.
 */
public class LocationNotFoundException extends EntityNotFoundException {

    public LocationNotFoundException(Long id) {
        super("Location", id);
    }

    public LocationNotFoundException(String message) {
        super(message);
    }
}
