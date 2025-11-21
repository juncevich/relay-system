package com.relay.unit.db;

import com.relay.db.entity.items.Relay;
import com.relay.db.repository.RelayRepository;
import com.relay.unit.GenericUnitTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
class RelayRepositoryTest extends GenericUnitTest {

  @Autowired
  private RelayRepository relayRepository;

  private final OffsetDateTime creationDate =
      OffsetDateTime.of(LocalDateTime.of(2020, 11, 18, 23, 15), ZoneOffset.ofHours(3));

  private final OffsetDateTime lastCheckDate =
      OffsetDateTime.of(LocalDateTime.of(2020, 11, 19, 23, 15), ZoneOffset.ofHours(3));
  private Relay defaultRelay;

  @BeforeEach
  void setUp() {
    defaultRelay = new Relay();
    defaultRelay.setCreatedAt(creationDate);
    defaultRelay.setLastCheckDate(lastCheckDate);
    relayRepository.save(defaultRelay);
  }

  @Test
  void successRelaySaving() {
    Relay relay = new Relay();
    relay.setSerialNumber("12345");

    Relay savedRelay = relayRepository.save(relay);
    assertThat(savedRelay.getSerialNumber()).isEqualTo("12345");
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
    Assertions.assertNull(savedRelay);
  }


  @Test
  void testIncorrectFindByLastCheckDate() {

    Page<@NotNull Relay> relayPage = relayRepository.findByLastCheckDate(
        lastCheckDate.plusDays(1).toLocalDate(), PageRequest.of(0, 1));
    assertTrue(relayPage.get().findFirst().isEmpty());
  }

  @Test
  void testNotFindByCreationDate() {

    Page<@NotNull Relay> relayPage = relayRepository
        .findByCreationDate(creationDate.plusDays(1).toLocalDate(), PageRequest.of(0, 1));
    assertTrue(relayPage.get().findFirst().isEmpty());
  }

  @Disabled
  @Test
  void testCorrectFindByLastCheckDate() {

    Page<@NotNull Relay> relayPage = relayRepository.findByLastCheckDate(lastCheckDate.toLocalDate(),
        PageRequest.of(0, 1));
    assertEquals(1, relayPage.getTotalElements());
    assertEquals(defaultRelay, relayPage.get().findFirst().orElseThrow());
  }

  @Disabled
  @Test
  void testFindByCreationDate() {

    Page<@NotNull Relay> relayPage = relayRepository.findByCreationDate(creationDate.toLocalDate(),
        PageRequest.of(0, 1));
    assertEquals(1, relayPage.getTotalElements());
    assertEquals(defaultRelay, relayPage.get().findFirst().orElseThrow());
  }
}
