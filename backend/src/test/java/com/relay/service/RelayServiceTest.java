package com.relay.service;

import com.relay.model.Relay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelayServiceTest {

    @Autowired
    private RelayService relayService;

    @Test
    public void findAll() throws Exception {
        assertEquals(4, relayService.findAll().size());

    }

    @Test
    public void save() throws Exception {
        relayService.save(new Relay("fifth"));
        assertEquals(5, relayService.findAll().size());
    }

    @Test
    public void findExistingOne() throws Exception {
        Relay relay = relayService.findOne(1);
        assertNotNull(relay);
        assertEquals(1, relay.getId().intValue());
        assertEquals("first", relay.getText());
    }

    @Test
    public void findNotExistingOne() throws Exception {
        Relay relay = relayService.findOne(77);
        assertNotNull(relay);
        assertEquals(99, relay.getId().intValue());
        assertEquals("99", relay.getText());


    }
}