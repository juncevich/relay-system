package com.relaysystem.ms.sidecar.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class PostgresSidecarApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresSidecarApplication.class, args);
    }

}
