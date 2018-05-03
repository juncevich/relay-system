package com.relay.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;

public class RelayRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;

    @Test
    public void assertFindAllReturn4Relays() {

        List<Relay> block = relayRepository.findAll().collect(Collectors.toList()).block();
        assertNotNull(block);
        assertEquals(4, block.size());
    }

    @Test
    public void testCreationRelay() {

        relayRepository.deleteAll().subscribe();
        Relay relay = Relay.builder().text("Test text").build();
        Relay savedRelay = relayRepository.save(relay).block();
        System.out.println(savedRelay);
        assertNotNull(savedRelay);
    }

    @Ignore
    @Test
    public void findByTextSuccessFindTest() {

        List<Relay> test_relay =
                relayRepository.findByText("Test relay").toStream().collect(Collectors.toList());
        assertEquals(1, test_relay.size());
        assertEquals("Test relay", test_relay.get(0).getText());
    }
}
