package com.relaysystem.ms.relayservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("relay-service")
public class Configuration {
    private String region;
}
