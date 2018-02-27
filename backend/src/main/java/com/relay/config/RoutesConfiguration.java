package com.relay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.relay.repository.RelayRepository;

@Configuration
public class RoutesConfiguration {

    @Bean
    RouterFunction<?> routes(RelayRepository relayRepository) {

        return null;
    }
}
