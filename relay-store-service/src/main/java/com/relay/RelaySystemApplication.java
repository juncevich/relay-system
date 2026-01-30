package com.relay;

import com.relay.config.CustomRecordConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CustomRecordConfig.class})
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
