package com.relay.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.relay.RelaySystemApplication;

@Configuration
@EnableJpaRepositories(basePackages = { "com.relay.repository" })
@EnableJpaAuditing
@EntityScan(basePackageClasses = { RelaySystemApplication.class, Jsr310Converters.class })
public class DbConfig {
}
