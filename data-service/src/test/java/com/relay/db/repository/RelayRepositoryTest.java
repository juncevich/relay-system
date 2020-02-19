package com.relay.db.repository;

import com.relay.db.entity.RelayEntity;
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
import java.time.Month;
import java.util.List;

import static com.relay.util.RelayTestUtil.createRelaysByStation;
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
        RelayEntity relay = new RelayEntity();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<RelayEntity> relayPage = relayRepository.findByCreationDate(creationDate, PageRequest.of(0, 1));
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    public void testNotFindByCreationDate() {
        RelayEntity relay = new RelayEntity();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<RelayEntity> relayPage = relayRepository.findByCreationDate(LocalDate.of(2020, 10, 11), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    public void testCorrectFindByCreationDateAfter() {
        RelayEntity relay = new RelayEntity();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<RelayEntity> relayPage = relayRepository.findByCreationDateAfter(LocalDate.of(2020, 10, 9), PageRequest.of(0, 1));
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    public void testIncorrectFindByCreationDateAfter() {
        RelayEntity relay = new RelayEntity();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<RelayEntity> relayPage = relayRepository.findByCreationDateAfter(LocalDate.of(2020, 10, 11), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    public void testCorrectFindByCreationDateBefore() {
        RelayEntity relay = new RelayEntity();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<RelayEntity> relayPage = relayRepository.findByCreationDateBefore(LocalDate.of(2020, 10, 11), PageRequest.of(0, 1));
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    public void testIncorrectFindByCreationDateBefore() {
        RelayEntity relay = new RelayEntity();
        LocalDate creationDate = LocalDate.of(2020, 10, 10);
        relay.setCreationDate(creationDate);
        relayRepository.save(relay);

        Page<RelayEntity> relayPage = relayRepository.findByCreationDateBefore(LocalDate.of(2020, 10, 9), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    public void testCorrectFindByLastCheckDate() {
        RelayEntity relay = new RelayEntity();
        LocalDateTime lastCheckDate = LocalDateTime.of(2020, 10, 10, 10, 10);
        relay.setLastCheckDate(lastCheckDate);
        relayRepository.save(relay);

        Page<RelayEntity> relayPage = relayRepository.findByLastCheckDate(lastCheckDate, PageRequest.of(0, 1));
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    public void testIncorrectFindByLastCheckDate() {
        RelayEntity relay = new RelayEntity();
        LocalDateTime lastCheckDate = LocalDateTime.of(2020, 10, 10, 10, 10);
        relay.setLastCheckDate(lastCheckDate);
        relayRepository.save(relay);

        Page<RelayEntity> relayPage = relayRepository.findByLastCheckDate(LocalDateTime.of(2020, 10, 10, 10, 11), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    public void testCorrectFindBySerialNumber() {
        RelayEntity relay = new RelayEntity();
        relay.setSerialNumber("12345");
        relayRepository.save(relay);

        RelayEntity savedRelay = relayRepository.findBySerialNumber("12345");
        assertEquals(relay, savedRelay);
    }

    @Test
    public void testIncorrectFindBySerialNumber() {
        RelayEntity relay = new RelayEntity();
        relay.setSerialNumber("12345");
        relayRepository.save(relay);

        RelayEntity savedRelay = relayRepository.findBySerialNumber("123456");
        assertNotEquals(relay, savedRelay);
    }

    @Test
    public void testCorrectTypeMapping() {
        RelayEntity relay = new RelayEntity();
        relay.setSerialNumber("12346");
        relay.setType(RelayType.NMSH_400);
        relayRepository.save(relay);

        RelayEntity savedRelay = relayRepository.findBySerialNumber("123456");
        assertNotEquals(relay, savedRelay);
    }

    @Test
    public void testFindRelaysByStation() {
        Station monetnajaStation = new Station();
        monetnajaStation.setName("Монетная");
        stationRepository.save(monetnajaStation);
        List<RelayEntity> relays = createRelaysByStation(5, monetnajaStation);
        relayRepository.saveAll(relays);

        Station berezitStation = new Station();
        berezitStation.setName("Березит");
        stationRepository.save(berezitStation);
        relays = createRelaysByStation(2, berezitStation);
        relayRepository.saveAll(relays);

        List<RelayEntity> monetnayaRelays = relayRepository.findRelaysByStationName("Монетная");
        assertNotNull(monetnayaRelays);
        assertEquals(5, monetnayaRelays.size());
    }


    @Test
    public void findByCreationDateAndStation() {
        LocalDate currentTime = LocalDate.of(2020, Month.DECEMBER, 20);
        RelayEntity relay1 = new RelayEntity();
        relay1.setCreationDate(currentTime.minusDays(1L));

        Station monetnajaStation = new Station();
        monetnajaStation.setName("Монетная");
        stationRepository.save(monetnajaStation);
        relayRepository.save(relay1);

        relayRepository.findRelaysByStationNameAndCreationDate("Монетная", currentTime.minusDays(1L));


    }

}
