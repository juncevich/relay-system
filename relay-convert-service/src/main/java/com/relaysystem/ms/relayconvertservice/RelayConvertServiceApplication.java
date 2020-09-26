package com.relaysystem.ms.relayconvertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.relaysystem.ms.relayconvertservice")
public class RelayConvertServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RelayConvertServiceApplication.class, args);
    }

//    @Bean
//    public Sampler defaultSampler() {
//
//        return Sampler.ALWAYS_SAMPLE;
//    }
}
