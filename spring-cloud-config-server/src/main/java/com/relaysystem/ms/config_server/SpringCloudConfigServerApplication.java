package com.relaysystem.ms.config_server;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.config.server.*;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication {
    public SpringCloudConfigServerApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServerApplication.class, args);
    }
}
