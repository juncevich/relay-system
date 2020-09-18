package com.relaysystem.ms.sidecar.postgres.config;

import com.relaysystem.ms.sidecar.postgres.healthcheck.SidecarHealthIndicator;
import com.relaysystem.ms.sidecar.postgres.healthcheck.postgres.PostgresHealthCheck;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @ConditionalOnProperty(name = "postgres.enabled", havingValue = "true")
    @Bean
    public SidecarHealthIndicator postgresHealthCheck() {
        return new PostgresHealthCheck();
    }
}
