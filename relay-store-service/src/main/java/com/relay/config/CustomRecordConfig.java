package com.relay.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("custom")
public record CustomRecordConfig(int customNumber, @NotNull CustomSubconfig subConfig) {

    public record CustomSubconfig(String customString) {
    }
}
