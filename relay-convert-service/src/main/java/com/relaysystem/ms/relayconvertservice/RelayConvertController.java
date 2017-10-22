package com.relaysystem.ms.relayconvertservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RelayConvertController {

    private final RelayExchangeServiceProxy exchangeServiceProxy;

    @Autowired
    public RelayConvertController(RelayExchangeServiceProxy exchangeServiceProxy) {
        this.exchangeServiceProxy = exchangeServiceProxy;
    }

    @GetMapping("/relay-convert/from/{from}/to/{to}")
    public RelayConversionBean convertRelay(@PathVariable String from, @PathVariable String to) {

        RelayConversionBean response = exchangeServiceProxy.retrieveExchangeValue(from, to);
        log.info("{}", response);
        return new RelayConversionBean(response.getId(), from, to);
    }
}
