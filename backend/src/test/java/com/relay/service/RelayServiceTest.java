package com.relay.service;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Relay;
import com.relay.repository.AbstractMongoDBTest;

public class RelayServiceTest extends AbstractMongoDBTest {

    @Autowired
    private RelayService relayService;

    @Test
    public void findAll() {

        List<Relay> block = relayService.findAll().collect(Collectors.toList()).block();
        assertEquals(5, block.size());

    }

    @Test
    public void save() {

        relayService.save(new Relay("fifth")).subscribe();
        assertEquals(6, relayService.findAll().collect(Collectors.toList()).block().size());
    }

    @Test
    public void findExistingOne() {

        List<Relay> relayList =
                relayService.findByText("Text1").collect(Collectors.toList()).block();
        Relay relay = relayList.get(0);
        assertNotNull(relay);
        assertEquals("Text1", relay.getText());
    }

    @Test
    public void findNotExistingOne() {

        Relay relay = relayService.findOne("77").block();
        assertNull(relay);

    }
}
