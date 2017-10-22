package com.relaysystem.ms.relayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class RelayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelayServiceApplication.class, args);
    }
}
