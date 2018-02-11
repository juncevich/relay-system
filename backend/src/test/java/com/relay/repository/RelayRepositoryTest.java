package com.relay.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;

public class RelayRepositoryTest extends AbsrtactRepositoryTest {

    @Autowired
    RelayRepository relayRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findByTextSucsessFindTest() {

        List<Relay> test_relay = relayRepository.findByText("Test relay");
        assertEquals(1, test_relay.size());
        assertEquals("Test relay", test_relay.get(0).getText());
    }
}
