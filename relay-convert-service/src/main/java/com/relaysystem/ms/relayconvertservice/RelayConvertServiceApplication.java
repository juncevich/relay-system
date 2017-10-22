package com.relaysystem.ms.relayconvertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@SpringBootApplication
//@EnableFeignClients("com.relaysystem.ms.relayconvertservice")
@EnableDiscoveryClient
public class RelayConvertServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelayConvertServiceApplication.class, args);
    }
}
