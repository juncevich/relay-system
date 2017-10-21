package com.relaysystem.ms.relayconvertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableFeignClients("com.relaysystem.ms.relayconvertservice")
@SpringBootApplication
public class RelayConvertServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelayConvertServiceApplication.class, args);
    }
}
