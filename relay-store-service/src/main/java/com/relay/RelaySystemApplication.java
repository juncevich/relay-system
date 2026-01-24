package com.relay;

import com.relay.config.CustomRecordConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
//import org.springframework.cloud.netflix.ribbon.RibbonClients;

// @EnableDiscoveryClient
//@KubernetesApplication
@SpringBootApplication
@EnableConfigurationProperties({CustomRecordConfig.class})
//@AutoConfigureAfter(RibbonAutoConfiguration.class)
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
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
