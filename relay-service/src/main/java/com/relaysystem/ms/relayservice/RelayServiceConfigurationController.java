package com.relaysystem.ms.relayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelayServiceConfigurationController {
    private final Configuration configuration;

    @Autowired
    public RelayServiceConfigurationController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/region")
    public RelayServiceConfiguration retrieveInfoFromConfiguration() {
        return new RelayServiceConfiguration(configuration.getRegion());
    }
}
