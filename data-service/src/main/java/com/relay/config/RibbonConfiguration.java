package com.relay.config;

//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.Server;
//import com.netflix.loadbalancer.ServerList;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@RequiredArgsConstructor
public class RibbonConfiguration {

    private DiscoveryClient discoveryClient;

    //    private String serviceId = "client";
    protected static final String VALUE_NOT_SET = "__not__set__";
    protected static final String DEFAULT_NAMESPACE = "ribbon";

//    public RibbonConfiguration() {
//    }

//    public RibbonConfiguration(String serviceId) {
//        this.serviceId = serviceId;
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public ServerList<?> ribbonServerList(IClientConfig config) {
//
//        Server[] servers = discoveryClient.getInstances(config.getClientName()).stream()
//                .map(i -> new Server(i.getHost(), i.getPort()))
//                .toArray(Server[]::new);
//
//        return new StaticServerList(servers);
//    }
}
