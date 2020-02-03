package com.relay.db.repository;

import com.relay.db.entity.Relay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RelayRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;


    @Test
    public void testSave() {
        Relay relay = new Relay();

        relayRepository.save(relay);

        Relay savedRelay = relayRepository.findById(relay.getId()).orElseThrow();
        assertEquals(relay, savedRelay);
    }
}
