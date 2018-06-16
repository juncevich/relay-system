package com.relay.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.relay.AbstractDBTest;
import com.relay.model.Relay;
import com.relay.repository.RelayRepository;

public class RelayServiceTest extends AbstractDBTest {

    @Autowired
    private RelayService relayService;

    @Autowired
    private RelayRepository relayRepository;

    @Before
    public void setUp() {

        relayRepository.deleteAll();
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
    public void testFindRelayBeforeDateOfManufacture() {

        Relay equalsRelay = new Relay();
        equalsRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 5));
        relayService.save(equalsRelay);

        Relay lessRelay = new Relay();
        lessRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
        relayService.save(lessRelay);

        Relay moreRelay = new Relay();
        moreRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 7));
        relayService.save(moreRelay);

        List<Relay> foundedRelayList =
                relayService.findByDateOfManufactureBefore(LocalDate.of(2018, Month.JUNE, 6));
        assertEquals(1, foundedRelayList.size());
        assertEquals(equalsRelay.getId(), foundedRelayList.get(0).getId());

    }

    @Test
    public void findAll() {

        ArrayList<Relay> relayList = Lists.newArrayList(relayService.findAll());
        assertEquals(0, relayList.size());

        Relay relay = Relay.builder().serialNumber("012345").build();
        relayService.save(relay);
        relay = Relay.builder().serialNumber("012345").build();
        relayService.save(relay);
        relay = Relay.builder().serialNumber("012345").build();
        relayService.save(relay);
        relay = Relay.builder().serialNumber("012345").build();
        relayService.save(relay);

        relayList = Lists.newArrayList(relayService.findAll());
        assertEquals(4, relayList.size());

    }

    @Test
    public void save() {

        ArrayList<Relay> relays = Lists.newArrayList(relayService.findAll());
        assertEquals(0, relays.size());

        relayService.save(new Relay());
        relays = Lists.newArrayList(relayService.findAll());
        assertEquals(1, relays.size());
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

        Relay relay = Relay.builder().serialNumber("012345").build();

        Relay savedRelay = relayService.save(relay);

        Relay foundedRelay = relayService.findBySerialNumber("012345");
        assertNotNull(foundedRelay);
        assertEquals(savedRelay.getId(), foundedRelay.getId());
    }

    @Test
    public void testDeletingRelayById() {

        Relay relay = Relay.builder().serialNumber("012345").build();
        relayService.save(relay);
        relay = Relay.builder().serialNumber("0123456").build();
        Relay savedRelay = relayService.save(relay);

        assertEquals(2, Lists.newArrayList(relayService.findAll()).size());

        relayService.deleteById(savedRelay.getId());

        assertEquals(1, Lists.newArrayList(relayService.findAll()).size());

    }

    @Test
    public void testSaveAll() {

        ArrayList<Relay> relayList = Lists.newArrayList(relayService.findAll());
        assertEquals(0, relayList.size());

        Relay relay1 = new Relay();
        Relay relay2 = new Relay();
        Relay relay3 = new Relay();
        Relay relay4 = new Relay();
        Relay relay5 = new Relay();

        List<Relay> relayToSave = Lists.newArrayList(relay1, relay2, relay3, relay4, relay5);
        relayService.saveAll(relayToSave);

        relayList = Lists.newArrayList(relayService.findAll());
        assertEquals(5, relayList.size());
    }

}
