package com.relaysystem.ms.relayexchangeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelayExchangeController {

    @GetMapping("/relay-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        return new ExchangeValue(1000L, from, to, from + to);
    }
}
