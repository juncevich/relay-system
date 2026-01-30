package com.relay.integration;

import com.relay.core.service.RelayService;
import com.relay.web.model.Relay;
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
        Relay relay = new Relay();
        relay.setCreatedAt(OffsetDateTime.of(LocalDateTime.of(2018, Month.JUNE, 6, 23, 15),
                ZoneOffset.ofHours(3)));
        relayService.save(relay);

        List<Relay> foundedRelayList =
                relayService.findByCreationDate(LocalDate.of(2018, Month.JUNE, 6), PageRequest.of(0, 10));

        assertEquals(relay.getCreatedAt(), foundedRelayList.get(0).getCreatedAt());
    }

    @Test
    void findRelayByLastCheckDate() {
        Relay relay = new Relay();
        relay.setVerificationDate(OffsetDateTime.of(LocalDateTime.of(2018, Month.JUNE, 6, 23, 15),
                ZoneOffset.ofHours(3)));
        relayService.save(relay);

        List<Relay> foundedRelayList =
                relayService.findByLastCheckDate(LocalDate.of(2018, Month.JUNE, 6), PageRequest.of(0, 10));

        assertEquals(relay.getVerificationDate(), foundedRelayList.get(0).getVerificationDate());
    }

    @Test
    void findAll() {
        Relay relay1 = Relay.builder().serialNumber("012345").build();
        relayService.save(relay1);
        Relay relay2 = Relay.builder().serialNumber("012346").build();
        relayService.save(relay2);
        Relay relay3 = Relay.builder().serialNumber("012347").build();
        relayService.save(relay3);
        Relay relay4 = Relay.builder().serialNumber("012348").build();
        relayService.save(relay4);

        List<Relay> relayList = relayService.findAll(PageRequest.of(0, 10));
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
