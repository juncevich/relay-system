package com.relay.db.repository;

import com.relay.db.entity.Relay;
import com.relay.db.entity.RelayType;
import com.relay.db.entity.location.Station;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RelayRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void testFindByCreationDate() {
        Relay relay = new Relay();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<Relay> relayPage = relayRepository.findByCreationDate(creationDate, PageRequest.of(0, 1));
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    public void testNotFindByCreationDate() {
        Relay relay = new Relay();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<Relay> relayPage = relayRepository.findByCreationDate(LocalDate.of(2020, 10, 11), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    public void testCorrectFindByCreationDateAfter() {
        Relay relay = new Relay();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<Relay> relayPage = relayRepository.findByCreationDateAfter(LocalDate.of(2020, 10, 9), PageRequest.of(0, 1));
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    public void testIncorrectFindByCreationDateAfter() {
        Relay relay = new Relay();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<Relay> relayPage = relayRepository.findByCreationDateAfter(LocalDate.of(2020, 10, 11), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    public void testCorrectFindByCreationDateBefore() {
        Relay relay = new Relay();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<Relay> relayPage = relayRepository.findByCreationDateBefore(LocalDate.of(2020, 10, 11), PageRequest.of(0, 1));
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    public void testIncorrectFindByCreationDateBefore() {
        Relay relay = new Relay();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<Relay> relayPage = relayRepository.findByCreationDateBefore(LocalDate.of(2020, 10, 9), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    public void testCorrectFindByLastCheckDate() {
        Relay relay = new Relay();
        LocalDateTime lastCheckDate = LocalDateTime.of(2020, 10, 10, 10, 10);
        relay.setLastCheckDate(lastCheckDate);
        relayRepository.save(relay);

        Page<Relay> relayPage = relayRepository.findByLastCheckDate(lastCheckDate, PageRequest.of(0, 1));
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    public void testIncorrectFindByLastCheckDate() {
        Relay relay = new Relay();
        LocalDateTime lastCheckDate = LocalDateTime.of(2020, 10, 10, 10, 10);
        relay.setLastCheckDate(lastCheckDate);
        relayRepository.save(relay);

        Page<Relay> relayPage = relayRepository.findByLastCheckDate(LocalDateTime.of(2020, 10, 10, 10, 11), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    public void testCorrectFindBySerialNumber() {
        Relay relay = new Relay();
        relay.setSerialNumber("12345");
        relayRepository.save(relay);

        Relay savedRelay = relayRepository.findBySerialNumber("12345");
        assertEquals(relay, savedRelay);
    }

    @Test
    public void testIncorrectFindBySerialNumber() {
        Relay relay = new Relay();
        relay.setSerialNumber("12345");
        relayRepository.save(relay);

        Relay savedRelay = relayRepository.findBySerialNumber("123456");
        assertNotEquals(relay, savedRelay);
    }

    @Test
    public void testCorrectTypeMapping() {
        Relay relay = new Relay();
        relay.setSerialNumber("12346");
        relay.setType(RelayType.NMSH_400);
        relayRepository.save(relay);

        Relay savedRelay = relayRepository.findBySerialNumber("123456");
        assertNotEquals(relay, savedRelay);
    }

    @Test
    public void testFindRelaysByStation() {
        Station monetnajaStation = new Station();
        monetnajaStation.setName("Монетная");
        stationRepository.save(monetnajaStation);
        List<Relay> relays = createRelaysByStation(5, monetnajaStation);
        relayRepository.saveAll(relays);

        Station berezitStation = new Station();
        berezitStation.setName("Березит");
        stationRepository.save(berezitStation);
        relays = createRelaysByStation(2, berezitStation);
        relayRepository.saveAll(relays);

        List<Relay> monetnayaRelays = relayRepository.findRelaysByStationName("Монетная");
        assertNotNull(monetnayaRelays);
        assertEquals(5, monetnayaRelays.size());
    }

    private List<Relay> createRelaysByStation(int stationCount, Station station) {
        List<Relay> relays = new ArrayList<>(stationCount);
        for (int j = 0; j < stationCount; j++) {
            Relay relay = Relay.builder()
                    .station(station)
                    .build();
            relays.add(relay);
        }
        return relays;
    }


}
