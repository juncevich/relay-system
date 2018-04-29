package com.relay.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;

import reactor.core.publisher.Mono;

public class RelayRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;

    @Before
    public void setUp() {

        // if (relayRepository.count().block() == 0L) {
        // Relay relay1 = new Relay("Text1");
        // Relay relay2 = new Relay("Text2");
        // Relay relay3 = new Relay("Text3");
        // Relay relay4 = new Relay("Text4");
        //
        // List<Relay> relayToSave = List.of(relay1, relay2, relay3, relay4);
        // relayRepository.saveAll(relayToSave).subscribe();
        // } else {
        // relayRepository.deleteAll().subscribe();
        // }
    }

    @Test
    public void assertEmptyRelayList() {

        List<Relay> block = relayRepository.findAll().collect(Collectors.toList()).block();
        assertEquals(4, block.size());
    }

    @Test
    public void testCreationRelay() {

        relayRepository.deleteAll().subscribe();
        Relay relay = Relay.builder().text("Test text").build();
        Mono<Relay> savedRelayMono = relayRepository.save(relay);
        Relay savedRelay = savedRelayMono.block();
        System.out.println(savedRelay);
        assertNotNull(savedRelay);
    }

    @Ignore
    @Test
    public void findByTextSucsessFindTest() {

        List<Relay> test_relay =
                relayRepository.findByText("Test relay").toStream().collect(Collectors.toList());
        assertEquals(1, test_relay.size());
        assertEquals("Test relay", test_relay.get(0).getText());
    }
}
