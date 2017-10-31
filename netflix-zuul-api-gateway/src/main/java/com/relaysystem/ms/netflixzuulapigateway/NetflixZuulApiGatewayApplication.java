package com.relaysystem.ms.netflixzuulapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class NetflixZuulApiGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(NetflixZuulApiGatewayApplication.class, args);
    }

    @Bean
    public AlwaysSampler defaultSampler() {

        return new AlwaysSampler();
    }
}
