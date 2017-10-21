package com.relaysystem.ms.relayconvertservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelayConvertController {

    @Autowired
    private RelayExchangeServiceProxy exchangeServiceProxy;

    @GetMapping("/relay-convert/from/{from}/to/{to}")
    public RelayConversionBean convertRelay(@PathVariable String from, @PathVariable String to) {

        RelayConversionBean response = exchangeServiceProxy.retrieveExchangeValue(from, to);
        return new RelayConversionBean(response.getId(), from, to);
    }
}
