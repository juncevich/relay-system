package com.relay.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RelayRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void assertEmptyRelayList() {
        List<Relay> all = relayRepository.findAll();
        assertEquals(0, all.size());
    }

    @Ignore
    @Test
    public void findByTextSucsessFindTest() {

        List<Relay> test_relay = relayRepository.findByText("Test relay");
        assertEquals(1, test_relay.size());
        assertEquals("Test relay", test_relay.get(0).getText());
    }
}
