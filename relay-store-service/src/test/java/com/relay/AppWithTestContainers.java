package com.relay;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Profile;

@Profile("dev")
public class AppWithTestContainers {

    static void main(String[] args) {
        SpringApplication.from(RelaySystemApplication::main).with(TestContainersConfiguration.class).run(args);
    }
}
