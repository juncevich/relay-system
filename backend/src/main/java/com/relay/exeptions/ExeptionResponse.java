package com.relay.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExeptionResponse {
    private Date timestamp;
    private String Message;
    private String detail;
}
