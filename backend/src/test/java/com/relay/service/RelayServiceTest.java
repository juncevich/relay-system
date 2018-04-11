package com.relay.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.repository.AbstractRepositoryTest;

public class RelayServiceTest extends AbstractRepositoryTest {

    @Autowired
    private RelayService relayService;

    @Test
    public void findAll() {

        long actualRelayCountInDB = relayService.findAll().toStream().count();
        assertEquals(4, actualRelayCountInDB);

    }
    //
    // @Test
    // public void save() {
    //
    // relayService.save(new Relay("fifth"));
    // assertEquals(5, relayService.findAll().size());
    // }
    //
    // @Test
    // public void findExistingOne() {
    //
    // Relay relay = relayService.findOne(1);
    // assertNotNull(relay);
    // assertEquals(1, relay.getId().intValue());
    // assertEquals("first", relay.getText());
    // }
    //
    // @Test
    // public void findNotExistingOne() {
    //
    // Relay relay = relayService.findOne(77);
    // assertNull(relay);
    //
    // }
}
