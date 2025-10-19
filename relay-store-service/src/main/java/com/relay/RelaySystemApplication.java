package com.relay;

import com.relay.config.CustomRecordConfig;
import com.relay.db.entity.items.Relay;
import com.relay.db.repository.RelayRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
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

    @Component
    public static class Runner implements CommandLineRunner {
        private final RelayRepository relayRepository;

        public Runner(RelayRepository relayRepository) {
            this.relayRepository = relayRepository;
        }

        @Override
        public void run(String... args) {
            Relay relay1 = Relay.builder()
//                    .id(1L)
                    .version(1L)
                    .createdAt(OffsetDateTime.now().minusDays(10))
                    .serialNumber(UUID.randomUUID().toString())
                    .lastCheckDate(OffsetDateTime.now().plusDays(5))
                    .updatedAt(OffsetDateTime.now().minusDays(5))
                    .build();
            Relay relay2 = Relay.builder()
//                    .id(2L)
                    .version(1L)
                    .createdAt(OffsetDateTime.now().minusDays(10))
                    .serialNumber(UUID.randomUUID().toString())
                    .lastCheckDate(OffsetDateTime.now().plusDays(5))
                    .build();
            relayRepository.saveAll(List.of(relay1, relay2));
        }
    }

}
