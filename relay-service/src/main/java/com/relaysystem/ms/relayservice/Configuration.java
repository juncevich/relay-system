package com.relaysystem.ms.relayservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("relay-service")
public class Configuration {

    private String region;
}
