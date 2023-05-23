package com.relay;

import org.springframework.boot.SpringApplication;

public class AppWithTestContainers {

    public static void main(String[] args) {
        SpringApplication.from(RelaySystemApplication::main).with(TestContainersConfiguration.class).run(args);
    }
}
