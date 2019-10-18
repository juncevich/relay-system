package com.relaysystem.ms.discovery;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.netflix.eureka.server.*;

@EnableEurekaServer
@SpringBootApplication
public class RsDiscoveryApplication {

    public static void main(String[] args) {

        SpringApplication.run(RsDiscoveryApplication.class, args);
    }
}
