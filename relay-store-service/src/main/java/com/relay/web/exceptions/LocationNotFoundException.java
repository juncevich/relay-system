package com.relay.web.exceptions;

/**
 * @deprecated Use {@link com.relay.core.exceptions.LocationNotFoundException} instead.
 */
@Deprecated(forRemoval = true)
public class LocationNotFoundException extends com.relay.core.exceptions.LocationNotFoundException {

    public LocationNotFoundException(Long id) {
        super(id);
    }

    public LocationNotFoundException(String message) {
        super(message);
    }
}
