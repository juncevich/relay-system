package com.relay.unit.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;

import com.google.common.collect.Lists;
import com.relay.web.model.Relay;
import com.relay.db.repository.RelayRepository;
import com.relay.service.RelayService;

@RunWith(MockitoJUnitRunner.class)
public class RelayServiceTest {

    @InjectMocks
    private RelayService relayService;

    @Mock
    private RelayRepository relayRepository;

    @Test
    public void findRelayByDate() {
        Relay relay = new Relay();
        relay.setId(BigInteger.valueOf(1));
        relay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
//        when(relayRepository.findByDateOfManufacture(any(), any()))
//                .thenReturn(new PageImpl<>(Lists.newArrayList(relay)));


        List<Relay> foundedRelayList = relayService
                .findByDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6)).getContent();

        assertEquals(relay.getId(), foundedRelayList.get(0).getId());
        assertEquals(relay.getDateOfManufacture(), foundedRelayList.get(0).getDateOfManufacture());
    }

    @Ignore
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

        List<Relay> foundedRelayList = relayService
                .findByDateOfManufactureAfter(LocalDate.of(2018, Month.JUNE, 6)).getContent();
        assertEquals(1, foundedRelayList.size());
        assertEquals(savedMoreRelay.getId(), foundedRelayList.get(0).getId());

    }

    @Ignore
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

        List<Relay> foundedRelayList = relayService
                .findByDateOfManufactureBefore(LocalDate.of(2018, Month.JUNE, 6)).getContent();
        assertEquals(1, foundedRelayList.size());
        assertEquals(equalsRelay.getId(), foundedRelayList.get(0).getId());

    }

    @Test
    public void findAll() {

        Relay relay1 = Relay.builder().serialNumber("012345").build();
        Relay relay2 = Relay.builder().serialNumber("012345").build();
        Relay relay3 = Relay.builder().serialNumber("012345").build();
        Relay relay4 = Relay.builder().serialNumber("012345").build();
        ArrayList<Relay> relays = Lists.newArrayList(relay1, relay2, relay3, relay4);
        when(relayService.findAll()).thenReturn(new PageImpl<>(relays));

        List<Relay> relayList = relayService.findAll().getContent();
        assertEquals(4, relayList.size());

    }


}
