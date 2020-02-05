package com.relay.db.repository;

import com.relay.db.entity.Relay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RelayRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;

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
}
