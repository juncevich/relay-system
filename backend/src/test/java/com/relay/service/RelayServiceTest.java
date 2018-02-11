package com.relay.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.relay.model.Relay;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelayServiceTest {

    @Autowired
    private RelayService relayService;

    @Test
    public void findAll() {

        assertEquals(4, relayService.findAll().size());

    }

    @Test
    public void save() {

        relayService.save(new Relay("fifth"));
        assertEquals(5, relayService.findAll().size());
    }

    @Test
    public void findExistingOne() {

        Relay relay = relayService.findOne(1);
        assertNotNull(relay);
        assertEquals(1, relay.getId().intValue());
        assertEquals("first", relay.getText());
    }

    @Test
    public void findNotExistingOne() {

        Relay relay = relayService.findOne(77);
        assertNull(relay);

    }
}
