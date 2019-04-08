package com.relay.integration;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import com.relay.integration.annotation.IntegrationTest;
import com.relay.model.Relay;
import com.relay.service.RelayService;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = { "com.relay" })
@ContextConfiguration(initializers = { RelayServiceITTest.Initializer.class })
@Category(IntegrationTest.class)
public class RelayServiceITTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer =
            (PostgreSQLContainer) new PostgreSQLContainer("postgres:10.4")
                    .withDatabaseName("sampledb").withUsername("sampleuser")
                    .withPassword("samplepwd").withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    private RelayService relayService;

    @Test
    public void findRelayByDate() {

        Relay relay = new Relay();
        relay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
        relayService.save(relay);

        List<Relay> foundedRelayList = relayService
                .findByDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6)).getContent();

        assertEquals(relay.getId(), foundedRelayList.get(0).getId());
        assertEquals(relay.getDateOfManufacture(), foundedRelayList.get(0).getDateOfManufacture());
    }

    @Test
    public void testFindRelayAfterDateOfManufacture() {

        Relay equalsRelay = new Relay();
        equalsRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 5));
        relayService.save(equalsRelay);

        Relay lessRelay = new Relay();
        lessRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
        relayService.save(lessRelay);

        Relay moreRelay = new Relay();
        moreRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 7));
        Relay savedMoreRelay = relayService.save(moreRelay);

        List<Relay> foundedRelayList = relayService
                .findByDateOfManufactureAfter(LocalDate.of(2018, Month.JUNE, 6)).getContent();
        assertEquals(1, foundedRelayList.size());
        assertEquals(savedMoreRelay.getId(), foundedRelayList.get(0).getId());

    }

    @Test
    public void testFindRelayBeforeDateOfManufacture() {

        Relay equalsRelay = new Relay();
        equalsRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 5));
        relayService.save(equalsRelay);

        Relay lessRelay = new Relay();
        lessRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
        relayService.save(lessRelay);

        Relay moreRelay = new Relay();
        moreRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 7));
        relayService.save(moreRelay);

        List<Relay> foundedRelayList = relayService
                .findByDateOfManufactureBefore(LocalDate.of(2018, Month.JUNE, 6)).getContent();
        assertEquals(1, foundedRelayList.size());
        assertEquals(equalsRelay.getId(), foundedRelayList.get(0).getId());

    }

    @Test
    public void findAll() {

        Relay relay1 = Relay.builder().serialNumber("012345").build();
        relayService.save(relay1);
        Relay relay2 = Relay.builder().serialNumber("012345").build();
        relayService.save(relay2);
        Relay relay3 = Relay.builder().serialNumber("012345").build();
        relayService.save(relay3);
        Relay relay4 = Relay.builder().serialNumber("012345").build();
        relayService.save(relay4);

        List<Relay> relayList = relayService.findAll().getContent();
        assertEquals(4, relayList.size());

    }

    public static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(
                @NotNull ConfigurableApplicationContext configurableApplicationContext) {

            TestPropertyValues
                    .of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                            "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                            "spring.datasource.password=" + postgreSQLContainer.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
