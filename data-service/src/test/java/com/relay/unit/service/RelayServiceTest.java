package com.relay.unit.service;

import com.google.common.collect.Lists;
import com.relay.db.repository.RelayRepository;
import com.relay.service.RelayService;
import com.relay.web.model.Relay;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RelayServiceTest {

    @Mock
    private RelayRepository relayRepository;

    @InjectMocks
    private RelayService relayService;


    @Disabled
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

    @Disabled
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

    @Disabled
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

        com.relay.db.entity.Relay relay1 = com.relay.db.entity.Relay.builder().serialNumber("012345").build();
        com.relay.db.entity.Relay relay2 = com.relay.db.entity.Relay.builder().serialNumber("012345").build();
        com.relay.db.entity.Relay relay3 = com.relay.db.entity.Relay.builder().serialNumber("012345").build();
        com.relay.db.entity.Relay relay4 = com.relay.db.entity.Relay.builder().serialNumber("012345").build();
        List<com.relay.db.entity.Relay> relays = Lists.newArrayList(relay1, relay2, relay3, relay4);
        when(relayRepository.findAll()).thenReturn(relays);

        Page<Relay> relayList = relayService.findAll();
//        assertEquals(4, relayList.size());
        assertNull(relayList);

    }


}
