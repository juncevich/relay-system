package com.relay.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;

import reactor.core.publisher.Flux;

public class RelayRepositoryTest extends AbsrtactRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void assertEmptyRelayList() {

        Flux<Relay> all = relayRepository.findAll();
        assertEquals(0, Objects.requireNonNull(all.count().block()).intValue());
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
