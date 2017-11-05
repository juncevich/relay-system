package com.relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// @EntityScan(basePackageClasses = { RelaySystemApplication.class, Jsr310Converters.class })
//@EnableDiscoveryClient

@SpringBootApplication
public class RelaySystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(RelaySystemApplication.class, args);
    }

}
