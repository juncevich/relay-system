package com.relaysystem.ms.relayexchangeservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeValue {
    private Long id;
    private String from;
    private String to;
    private String controlResult;
    private int port;

    public ExchangeValue(Long id, String from, String to, String controlResult) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.controlResult = controlResult;
    }
}
