package com.relay.unit.service;

import com.google.common.collect.Lists;
import com.relay.db.entity.RelayEntity;
import com.relay.db.entity.location.Station;
import com.relay.db.repository.RelayRepository;
import com.relay.service.RelayService;
import com.relay.web.model.Relay;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.relay.util.RelayTestUtil.createRelaysByStation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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

        RelayEntity relay1 = RelayEntity.builder().serialNumber("012345").build();
        RelayEntity relay2 = RelayEntity.builder().serialNumber("012345").build();
        RelayEntity relay3 = RelayEntity.builder().serialNumber("012345").build();
        RelayEntity relay4 = RelayEntity.builder().serialNumber("012345").build();
        List<RelayEntity> relays = Lists.newArrayList(relay1, relay2, relay3, relay4);
        when(relayRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(relays));

        List<Relay> relayList = relayService.findAll(PageRequest.of(0, 10));
        assertNotNull(relayList);
        assertEquals(4, relayList.size());

    }

    @Test
    public void findRelayByStationName() {
        Station monetnajaStation = new Station();
        List<RelayEntity> relaysByMonetnajaStation = createRelaysByStation(4, monetnajaStation);

        when(relayRepository.findRelaysByStationName("Монетная")).thenReturn(relaysByMonetnajaStation);

        List<Relay> relays = relayService.findByStationName("Монетная");
        assertNotNull(relays);
        assertEquals(4, relays.size());
    }


}
