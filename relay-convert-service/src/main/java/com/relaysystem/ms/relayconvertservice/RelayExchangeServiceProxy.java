package com.relaysystem.ms.relayconvertservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "relay-exchange-service", url = "localhost:8000")
public interface RelayExchangeServiceProxy {

    @GetMapping("/relay-exchange/from/{from}/to/{to}")
    public RelayConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
