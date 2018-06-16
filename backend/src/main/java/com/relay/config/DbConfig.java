package com.relay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "com.relay.repository" })
@EnableJpaAuditing
// @EntityScan(basePackageClasses = { RelaySystemApplication.class, Jsr310Converters.class })
public class DbConfig {
}
