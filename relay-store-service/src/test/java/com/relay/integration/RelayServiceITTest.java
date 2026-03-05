package com.relay.integration;

import com.relay.core.service.RelayService;
import com.relay.db.dao.LocationDao;
import com.relay.db.dao.RelayDao;
import com.relay.db.dao.StandDao;
import com.relay.db.entity.location.Station;
import com.relay.db.entity.storage.Stand;
import com.relay.db.entity.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@Tag("integration")
class RelayServiceITTest {

    @Autowired
    private RelayService relayService;
    @Autowired
    private RelayDao relayDao;
    @Autowired
    private LocationDao locationDao;
    @Autowired
    private StandDao standDao;

    private Storage defaultStorage;

    @BeforeEach
    void setUp() {
        relayDao.deleteAll();
        standDao.deleteAll();
        locationDao.deleteAll();

        Station station = new Station();
        station.setName("IT Test Station");
        locationDao.save(station);

        Stand stand = new Stand();
        stand.setName("IT Test Stand");
        stand.setLocation(station);
        standDao.save(stand);

        defaultStorage = stand;
    }

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
        relayService.save(relay, defaultStorage.getId());

        Page<com.relay.core.model.Relay> foundedRelayPage =
                relayService.findByCreationDate(LocalDate.of(2018, Month.JUNE, 6), PageRequest.of(0, 10));

        assertEquals(relay.createdAt(), foundedRelayPage.getContent().get(0).createdAt());
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
        relayService.save(relay, defaultStorage.getId());

        Page<com.relay.core.model.Relay> foundedRelayPage =
                relayService.findByLastCheckDate(LocalDate.of(2018, Month.JUNE, 6), PageRequest.of(0, 10));

        assertEquals(relay.lastCheckDate(), foundedRelayPage.getContent().get(0).lastCheckDate());
    }

    @Test
    void findAll() {
        com.relay.core.model.Relay relay1 = new com.relay.core.model.Relay(null, "012345", null, null, null, 0, null, null);
        relayService.save(relay1, defaultStorage.getId());
        com.relay.core.model.Relay relay2 = new com.relay.core.model.Relay(null, "012346", null, null, null, 0, null, null);
        relayService.save(relay2, defaultStorage.getId());
        com.relay.core.model.Relay relay3 = new com.relay.core.model.Relay(null, "012347", null, null, null, 0, null, null);
        relayService.save(relay3, defaultStorage.getId());
        com.relay.core.model.Relay relay4 = new com.relay.core.model.Relay(null, "012348", null, null, null, 0, null, null);
        relayService.save(relay4, defaultStorage.getId());

        Page<com.relay.core.model.Relay> relayPage = relayService.findAll(PageRequest.of(0, 10));
        assertEquals(4, relayPage.getTotalElements());
    }

}
