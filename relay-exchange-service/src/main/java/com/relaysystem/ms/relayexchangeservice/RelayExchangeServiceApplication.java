package com.relaysystem.ms.relayexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RelayExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelayExchangeServiceApplication.class, args);
	}
}
