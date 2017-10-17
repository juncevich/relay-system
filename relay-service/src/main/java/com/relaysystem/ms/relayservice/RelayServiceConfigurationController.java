package com.relaysystem.ms.relayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelayServiceConfigurationController {
    @GetMapping("/region")
    public RelayServiceConfiguration retrieveInfoFromConfiguration() {
        return new RelayServiceConfiguration("svrd");
    }
}
