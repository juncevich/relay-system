package com.relay.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RelayRepositoryTest extends AbsrtactRepositoryTest
{

    @Autowired
    private RelayRepository relayRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void assertEmptyRelayList() {

        Flux<Relay> all = relayRepository.findAll();
        assertEquals(4, Objects.requireNonNull(all.count().block()).intValue());
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
