package com.relay.repository;

import com.relay.model.Relay;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public class RelayRepositoryTest extends AbstractMongoDBTest {

    @Autowired
    private RelayRepository relayRepository;

    @Test
    public void assertFindAllReturn4Relays() {

        List<Relay> block = relayRepository.findAll().collect(Collectors.toList()).block();
        assertNotNull(block);
        assertEquals(5, block.size());
    }

    @Test
    public void testCreationRelay() {

        relayRepository.deleteAll().subscribe();
        Relay relay = Relay.builder().text("Test text").build();
        Relay savedRelay = relayRepository.save(relay).block();
        System.out.println(savedRelay);
        assertNotNull(savedRelay);
    }

    @Test
    public void findByTextSuccessFindTest() {

        List<Relay> test_relay =
                relayRepository.findByText("Text1").toStream().collect(Collectors.toList());
        assertEquals(1, test_relay.size());
        assertEquals("Text1", test_relay.get(0).getText());
    }
}
