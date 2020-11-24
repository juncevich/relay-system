package com.relay.db.repository;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.relay.db.entity.items.Relay;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
class RelayRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Relay relay;

    private OffsetDateTime creationDate;

    private OffsetDateTime lastCheckDate;

    @BeforeEach
    void setUp() {

        relay = new Relay();
        creationDate =
                OffsetDateTime.of(LocalDateTime.of(2020, 11, 18, 23, 15), ZoneOffset.ofHours(3));
        relay.setCreationDate(creationDate);
        lastCheckDate =
                OffsetDateTime.of(LocalDateTime.of(2020, 11, 19, 23, 15), ZoneOffset.ofHours(3));
        relay.setLastCheckDate(lastCheckDate);
        relayRepository.save(relay);
    }

    @Test
    void testFindByCreationDate() {

        Page<Relay> relayPage = relayRepository.findByCreationDate(creationDate.toLocalDate(),
                PageRequest.of(0, 1));
        assertEquals(1, relayPage.getTotalElements());
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    void testNotFindByCreationDate() {

        Page<Relay> relayPage = relayRepository
                .findByCreationDate(creationDate.plusDays(1).toLocalDate(), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    void testCorrectFindByLastCheckDate() {

        Page<Relay> relayPage = relayRepository.findByLastCheckDate(lastCheckDate.toLocalDate(),
                PageRequest.of(0, 1));
        assertEquals(1, relayPage.getTotalElements());
        assertEquals(relay, relayPage.get().findFirst().orElseThrow());
    }

    @Test
    void testIncorrectFindByLastCheckDate() {

        Page<Relay> relayPage = relayRepository.findByLastCheckDate(
                lastCheckDate.plusDays(1).toLocalDate(), PageRequest.of(0, 1));
        assertTrue(relayPage.get().findFirst().isEmpty());
    }

    @Test
    void testCorrectFindBySerialNumber() {

        Relay relay = new Relay();
        relay.setSerialNumber("12345");
        relayRepository.save(relay);

        Relay savedRelay = relayRepository.findBySerialNumber("12345");
        assertEquals(relay, savedRelay);
    }

    @Test
    void testIncorrectFindBySerialNumber() {

        Relay relay = new Relay();
        relay.setSerialNumber("12345");
        relayRepository.save(relay);

        Relay savedRelay = relayRepository.findBySerialNumber("123456");
        assertNull(savedRelay);
        assertNotEquals(relay, savedRelay);
    }
    //
    // @Test
    // public void testCorrectTypeMapping() {
    // RelayEntity relay = new RelayEntity();
    // relay.setSerialNumber("12346");
    // relay.setType(RelayType.NMSH_400);
    // relayRepository.save(relay);
    //
    // RelayEntity savedRelay = relayRepository.findBySerialNumber("123456");
    // assertNotEquals(relay, savedRelay);
    // }
    //
    // @Test
    // public void testFindRelaysByStation() {
    // Station monetnajaStation = Station.builder()
    // .name("Монетная")
    // .build();
    // testEntityManager.persist(monetnajaStation);
    // List<RelayEntity> relays = createRelaysByStation(5, monetnajaStation);
    // relayRepository.saveAll(relays);
    //
    // Station berezitStation = Station.builder()
    // .name("Березит")
    // .build();
    // testEntityManager.persist(berezitStation);
    // relays = createRelaysByStation(2, berezitStation);
    // relayRepository.saveAll(relays);
    //
    // List<RelayEntity> monetnayaRelays = relayRepository.findRelaysByStationName("Монетная");
    // assertNotNull(monetnayaRelays);
    // assertEquals(5, monetnayaRelays.size());
    // }
    //
    //
    // @Test
    // public void findByCreationDateAndStation() {
    // Station monetnajaStation = Station.builder()
    // .name("Монетная")
    // .build();
    // testEntityManager.persist(monetnajaStation);
    // LocalDate currentTime = LocalDate.of(2020, Month.DECEMBER, 20);
    // RelayEntity relay1 = new RelayEntity();
    // relay1.setCreationDate(currentTime.minusDays(1L));
    // relay1.setStation(monetnajaStation);
    //
    // relayRepository.save(relay1);
    //
    // RelayEntity relay2 = new RelayEntity();
    // relay2.setCreationDate(currentTime);
    // relay2.setStation(monetnajaStation);
    //
    // relayRepository.save(relay2);
    //
    // List<RelayEntity> relays =
    // relayRepository.findRelaysByStationNameAndCreationDate("Монетная",
    // currentTime.minusDays(1L));
    // assertNotNull(relays);
    // assertEquals(1, relays.size());
    //
    // }
    //
    // @Test
    // public void testCreationDateField() {
    // RelayEntity relayEntity = new RelayEntity();
    // relayRepository.save(relayEntity);
    //
    // assertNotNull(relayEntity.getCreated());
    // assertNull(relayEntity.getUpdated());
    //
    // }
}
