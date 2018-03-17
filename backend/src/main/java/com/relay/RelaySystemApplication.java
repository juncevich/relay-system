package com.relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.web.reactive.config.EnableWebFlux;

// @EnableDiscoveryClient
@SpringBootApplication
@EnableWebFlux
@ComponentScan(value = "com.relay")
@EntityScan(basePackageClasses = { RelaySystemApplication.class, Jsr310Converters.class })
public class RelaySystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(RelaySystemApplication.class, args);
    }

}
