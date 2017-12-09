package com.relaysystem.ms.zipkindistributedtracingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// TODO Fix zipkin service
// import zipkin.server.EnableZipkinServer;

// @EnableZipkinServer
@SpringBootApplication
public class ZipkinDistributedTracingServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZipkinDistributedTracingServerApplication.class, args);
    }
}
