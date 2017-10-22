package com.relaysystem.ms.relayexchangeservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RelayExchangeController {

    private final Environment environment;

    private final RelayExchangeRepository relayExchangeRepository;

    @Autowired
    public RelayExchangeController(Environment environment, RelayExchangeRepository relayExchangeRepository) {
        this.environment = environment;
        this.relayExchangeRepository = relayExchangeRepository;
    }

    @GetMapping("/relay-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchangeValue = relayExchangeRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        log.info("{}", exchangeValue);
        return exchangeValue;
    }
}
