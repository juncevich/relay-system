package com.relaysystem.ms.config_server;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.config.server.*;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    public ConfigServerApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
