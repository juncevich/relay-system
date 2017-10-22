package com.relaysystem.ms.relayconvertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.relaysystem.ms.relayconvertservice")
public class RelayConvertServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelayConvertServiceApplication.class, args);
    }
}
