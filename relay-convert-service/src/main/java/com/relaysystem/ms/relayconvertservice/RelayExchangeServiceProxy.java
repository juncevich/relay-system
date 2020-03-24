package com.relaysystem.ms.relayconvertservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cloud-api-gateway")
@RibbonClient(name = "relay-exchange-service")
public interface RelayExchangeServiceProxy {

    @GetMapping("/relay-exchange-service/relay-exchange/from/{from}/to/{to}")
    RelayConversionBean retrieveExchangeValue(@PathVariable("from") String from,
            @PathVariable("to") String to);
}
