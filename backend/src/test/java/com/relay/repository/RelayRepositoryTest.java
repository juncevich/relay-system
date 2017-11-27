package com.relay.repository;

import com.relay.model.Relay;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RelayRepositoryTest {
    @Autowired
    RelayRepository relayRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByTextSucsessFindTest() throws Exception {
        List<Relay> test_relay = relayRepository.findByText("Test relay");
        assertEquals(1, test_relay.size());
        assertEquals("Test relay", test_relay.get(0).getText());
    }
}