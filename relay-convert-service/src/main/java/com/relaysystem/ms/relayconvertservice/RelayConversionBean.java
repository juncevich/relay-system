package com.relaysystem.ms.relayconvertservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelayConversionBean {

    private Long id;

    @NonNull
    private String from;

    @NonNull
    private String to;

    @NonNull
    private Integer port;

    public RelayConversionBean(Long id, String from, String to) {

        this.id = id;
        this.from = from;
        this.to = to;
    }
}
