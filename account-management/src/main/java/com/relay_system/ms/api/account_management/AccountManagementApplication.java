package com.relay_system.ms.api.account_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccountManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagementApplication.class, args);
    }

}
