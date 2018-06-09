package com.relay.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.relay.AbstractDBTest;
import com.relay.model.Relay;

public class RelayServiceTest extends AbstractDBTest {

    @Autowired
    private RelayService relayService;

    @Override
    @Before
    public void setUp() {

        Relay relay1 = new Relay("Text");
        Relay relay2 = new Relay("Text1");
        Relay relay3 = new Relay("Text2");
        Relay relay4 = new Relay("Text3");
        Relay relay5 = new Relay("Text4");

        List<Relay> relayToSave = Arrays.asList(relay1, relay2, relay3, relay4, relay5);
        relayService.saveAll(relayToSave);
    }

    @Test
    public void findRelayByDate() {

        Relay relay = new Relay();
        relay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));

        Relay savedRelay = relayService.save(relay);

        List<Relay> foundedRelayList =
                relayService.findByDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));

        assertEquals(savedRelay.getId(), foundedRelayList.get(0).getId());
    }

    @Test
    public void testFindRelayAfterDateOfManufacture() {

        Relay equalsRelay = new Relay();
        equalsRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 5));
        relayService.save(equalsRelay);

        Relay lessRelay = new Relay();
        lessRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
        relayService.save(lessRelay);

        Relay moreRelay = new Relay();
        moreRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 7));
        Relay savedMoreRelay = relayService.save(moreRelay);

        List<Relay> foundedRelayList =
                relayService.findByDateOfManufactureAfter(LocalDate.of(2018, Month.JUNE, 6));
        assertEquals(1, foundedRelayList.size());
        assertEquals(savedMoreRelay.getId(), foundedRelayList.get(0).getId());

    }

    @Test
    public void findAll() {

        List<Relay> block = Lists.newArrayList(relayService.findAll());
        assertEquals(5, block.size());

    }

    @Test
    public void save() {

        relayService.save(new Relay("fifth"));
        ArrayList<Relay> relays = Lists.newArrayList(relayService.findAll());
        assertEquals(6, relays.size());
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

    @Test
    public void testFindByVerificationDate() {

        Relay relay = new Relay();
        relay.setVerificationDate(LocalDate.of(2018, Month.JUNE, 5));
        Relay savedRelay = relayService.save(relay);

        List<Relay> relayList =
                relayService.findByVerificationDate(LocalDate.of(2018, Month.JUNE, 5));
        assertEquals(1, relayList.size());
        assertEquals(savedRelay.getId(), relayList.get(0).getId());
    }

    @Test
    public void testFindBySerialNumber() {

        Relay relay = Relay.builder().serialNumber("45").text("some text").build();

        Relay savedRelay = relayService.save(relay);

        Relay foundedRelay = relayService.findBySerialNumber("45");
        assertNotNull(foundedRelay);
        assertEquals(savedRelay.getId(), foundedRelay.getId());
    }
}
