package com.relaysystem.ms.relayconvertservice;

import lombok.Data;

@Data
public class RelayConversionBean {

    private Long id;
    private String from;
    private String to;
    private int port;

    public RelayConversionBean(Long id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }
}
