package com.relaysystem.ms.relayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class RelayServiceConfigurationController {

    private final Configuration configuration;

    @Autowired
    public RelayServiceConfigurationController(Configuration configuration) {

        this.configuration = configuration;
    }

    @GetMapping("/region")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public RelayServiceConfiguration retrieveInfoFromConfiguration() {

        return new RelayServiceConfiguration(new String());
        // return new RelayServiceConfiguration(configuration.getRegion());
    }

    public String fallbackMethod() {

        return "something wrong";
    }
}
