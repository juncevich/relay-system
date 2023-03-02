package com.relay.web.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private Instant timestamp;

    private String message;

    private String detail;
}
