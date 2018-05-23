package com.relay.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import com.relay.repository.AbstractMongoDBTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;
import com.relay.repository.AbstractRepositoryTest;

public class RelayServiceTest extends AbstractMongoDBTest {

    @Autowired
    private RelayService relayService;

    @Test
    public void findAll() {

        List<Relay> block = relayService.findAll().collect(Collectors.toList()).block();
        assertEquals(5, block.size());

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
