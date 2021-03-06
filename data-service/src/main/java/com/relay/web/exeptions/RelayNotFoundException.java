package com.relay.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RelayNotFoundException extends RuntimeException {

    public RelayNotFoundException(String message) {

        super(message);
    }
}
