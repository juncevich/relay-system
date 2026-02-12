package com.relay.unit.db;

import com.relay.db.dao.LocationDao;
import com.relay.db.dao.RelayDao;
import com.relay.db.dao.StandDao;
import com.relay.db.entity.items.Relay;
import com.relay.db.entity.location.Station;
import com.relay.db.entity.storage.Stand;
import com.relay.db.entity.storage.Storage;
import com.relay.unit.GenericUnitTest;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class RelayRepositoryTest extends GenericUnitTest {

  @Autowired
  private RelayDao relayRepository;

  @Autowired
  private LocationDao locationRepository;

  @Autowired
  private StandDao standRepository;

  private final OffsetDateTime creationDate =
      OffsetDateTime.of(LocalDateTime.of(2020, 11, 18, 23, 15), ZoneOffset.ofHours(3));

  private final OffsetDateTime lastCheckDate =
      OffsetDateTime.of(LocalDateTime.of(2020, 11, 19, 23, 15), ZoneOffset.ofHours(3));
  private Relay defaultRelay;
  private Storage defaultStorage;

  @BeforeEach
  void setUp() {
    Station station = new Station();
    station.setName("Test Station");
    locationRepository.save(station);

    Stand stand = new Stand();
    stand.setName("Test Stand");
    stand.setLocation(station);
    standRepository.save(stand);

    defaultStorage = stand;
    defaultRelay = Relay.builder()
            .storage(defaultStorage)
            .build();
    defaultRelay.setCreatedAt(creationDate);
    defaultRelay.setLastCheckDate(lastCheckDate);
    relayRepository.save(defaultRelay);
  }

  @Test
  void successRelaySaving() {
    Relay relay = new Relay();
    relay.setSerialNumber("12345");
    relay.setStorage(defaultStorage);

    Relay savedRelay = relayRepository.save(relay);
    assertThat(savedRelay.getSerialNumber()).isEqualTo("12345");
  }

  @Test
  void testCorrectFindBySerialNumber() {

    Relay relay = new Relay();
    relay.setSerialNumber("12345");
    relay.setStorage(defaultStorage);
    relayRepository.save(relay);

    Relay savedRelay = relayRepository.findBySerialNumber("12345");
    assertEquals(relay, savedRelay);
  }

  @Test
  void testIncorrectFindBySerialNumber() {

    Relay relay = new Relay();
    relay.setSerialNumber("12345");
    relay.setStorage(defaultStorage);
    relayRepository.save(relay);

    Relay savedRelay = relayRepository.findBySerialNumber("123456");
    Assertions.assertNull(savedRelay);
  }


  @Test
  void testIncorrectFindByLastCheckDate() {

      Page<@NonNull Relay> relayPage = relayRepository.findByLastCheckDate(
        lastCheckDate.plusDays(1).toLocalDate(), PageRequest.of(0, 1));
    assertTrue(relayPage.get().findFirst().isEmpty());
  }

  @Test
  void testNotFindByCreationDate() {

      Page<@NonNull Relay> relayPage = relayRepository
        .findByCreationDate(creationDate.plusDays(1).toLocalDate(), PageRequest.of(0, 1));
    assertTrue(relayPage.get().findFirst().isEmpty());
  }

  @Disabled
  @Test
  void testCorrectFindByLastCheckDate() {

      Page<@NonNull Relay> relayPage = relayRepository.findByLastCheckDate(lastCheckDate.toLocalDate(),
        PageRequest.of(0, 1));
    assertEquals(1, relayPage.getTotalElements());
    assertEquals(defaultRelay, relayPage.get().findFirst().orElseThrow());
  }

  @Disabled
  @Test
  void testFindByCreationDate() {

      Page<@NonNull Relay> relayPage = relayRepository.findByCreationDate(creationDate.toLocalDate(),
        PageRequest.of(0, 1));
    assertEquals(1, relayPage.getTotalElements());
    assertEquals(defaultRelay, relayPage.get().findFirst().orElseThrow());
  }
}
