package com.relay.integration;

import com.relay.core.service.RelayService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@DataJpaTest
@Testcontainers
@ComponentScan(basePackages = {"com.relay"})
@ContextConfiguration(initializers = {RelayServiceITTest.Initializer.class})
@Tag("integration")
class RelayServiceITTest {

    @Container
    static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer("postgres:18-alpine")
                    .withDatabaseName("sampledb").withUsername("sampleuser")
                    .withPassword("samplepwd").withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    private RelayService relayService;

    @Test
    void findRelayByCreationDate() {
        OffsetDateTime creationDate = OffsetDateTime.of(LocalDateTime.of(2018, Month.JUNE, 6, 23, 15),
                ZoneOffset.ofHours(3));
        com.relay.core.model.Relay relay = new com.relay.core.model.Relay(
                null,
                "12345",
                null,
                creationDate,
                null,
                0,
                null,
                null
        );
        relayService.save(relay);

        List<com.relay.core.model.Relay> foundedRelayList =
                relayService.findByCreationDate(LocalDate.of(2018, Month.JUNE, 6), PageRequest.of(0, 10));

        assertEquals(relay.createdAt(), foundedRelayList.get(0).createdAt());
    }

    @Test
    void findRelayByLastCheckDate() {
        OffsetDateTime lastCheckDate = OffsetDateTime.of(LocalDateTime.of(2018, Month.JUNE, 6, 23, 15),
                ZoneOffset.ofHours(3));
        com.relay.core.model.Relay relay = new com.relay.core.model.Relay(
                null,
                "12345",
                null,
                null,
                lastCheckDate,
                0,
                null,
                null
        );
        relayService.save(relay);

        List<com.relay.core.model.Relay> foundedRelayList =
                relayService.findByLastCheckDate(LocalDate.of(2018, Month.JUNE, 6), PageRequest.of(0, 10));

        assertEquals(relay.lastCheckDate(), foundedRelayList.get(0).lastCheckDate());
    }

    @Test
    void findAll() {
        com.relay.core.model.Relay relay1 = new com.relay.core.model.Relay(null, "012345", null, null, null, 0, null, null);
        relayService.save(relay1);
        com.relay.core.model.Relay relay2 = new com.relay.core.model.Relay(null, "012346", null, null, null, 0, null, null);
        relayService.save(relay2);
        com.relay.core.model.Relay relay3 = new com.relay.core.model.Relay(null, "012347", null, null, null, 0, null, null);
        relayService.save(relay3);
        com.relay.core.model.Relay relay4 = new com.relay.core.model.Relay(null, "012348", null, null, null, 0, null, null);
        relayService.save(relay4);

        List<com.relay.core.model.Relay> relayList = relayService.findAll(PageRequest.of(0, 10));
        assertEquals(4, relayList.size());
    }

    public static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(
                ConfigurableApplicationContext configurableApplicationContext) {

            TestPropertyValues
                    .of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                            "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                            "spring.datasource.password=" + postgreSQLContainer.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
