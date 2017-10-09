package com.relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RelaySystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(RelaySystemApplication.class, args);
    }

    @GetMapping(value = "/hello")
    public String getHello() {

        return "Hello!";
    }
}
