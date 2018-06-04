package com.relay.repository;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;

public class RelayRepositoryTest extends AbstractDBTest {

    @Autowired
    private RelayRepository relayRepository;

    @Test
    public void assertFindAllReturn4Relays() {

        List<Relay> block = Lists.newArrayList(relayRepository.findAll());
        assertNotNull(block);
        assertEquals(5, block.size());
    }

    @Test
    public void testCreationRelay() {

        relayRepository.deleteAll();
        Relay relay = Relay.builder().text("Test text").build();
        Relay savedRelay = relayRepository.save(relay);
        System.out.println(savedRelay);
        assertNotNull(savedRelay);
    }

    @Test
    public void findByTextSuccessFindTest() {

        Relay test_relay = relayRepository.findByText("Text1");
        assertEquals("Text1", test_relay.getText());
    }
}
