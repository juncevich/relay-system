package com.relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableDiscoveryClient

@SpringBootApplication
@EntityScan(basePackageClasses = {RelaySystemApplication.class, Jsr310Converters.class})
public class RelaySystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(RelaySystemApplication.class, args);
    }

}
