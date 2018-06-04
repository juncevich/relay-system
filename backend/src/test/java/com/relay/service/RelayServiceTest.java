package com.relay.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.relay.model.Relay;
import com.relay.repository.AbstractDBTest;

public class RelayServiceTest extends AbstractDBTest {

    @Autowired
    private RelayService relayService;

    @Test
    public void findAll() {

        List<Relay> block = Lists.newArrayList(relayService.findAll());
        assertEquals(5, block.size());

    }

    @Test
    public void save() {

        relayService.save(new Relay("fifth"));
        assertEquals(6, Lists.newArrayList(relayService.findAll()).size());
    }

    @Test
    public void findExistingOne() {

        Relay relay = relayService.findByText("Text1");
        assertNotNull(relay);
        assertEquals("Text1", relay.getText());
    }

    @Test
    public void findNotExistingOne() {

        Relay relay = relayService.findOne("77").orElse(null);
        assertNull(relay);

    }
}
