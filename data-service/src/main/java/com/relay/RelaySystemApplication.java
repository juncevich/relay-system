package com.relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.dekorate.kubernetes.annotation.KubernetesApplication;

// @EnableDiscoveryClient
@KubernetesApplication
@SpringBootApplication
public class RelaySystemApplication {

    /**
     * Main class
     * 
     * @param args
     *            args
     */
    public static void main(String[] args) {

        SpringApplication.run(RelaySystemApplication.class, args);
    }

}
