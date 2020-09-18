package com.relaysystem.ms.sidecar.postgres.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "postgres")
public class PostgresSettings {
    private String  userName;
    private Boolean enabled;
}
