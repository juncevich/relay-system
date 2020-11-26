package com.relay.unit.service;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.relay.db.entity.items.Relay;
import com.relay.db.repository.RelayRepository;
import com.relay.mappers.RelayMapper;
import com.relay.service.RelayService;

@ExtendWith(SpringExtension.class)
class RelayServiceTest {

    @Mock
    private RelayRepository relayRepository;

    @InjectMocks
    private RelayService relayService;

    @BeforeEach
    void setUp() {

        relayService = new RelayService(relayRepository, RelayMapper.INSTANCE);
    }

    @Test
    void findRelayByDate() {

        Relay relay = new Relay();
        relay.setId(1L);
        relay.setSerialNumber("12345");
        OffsetDateTime creationDate =
                OffsetDateTime.of(LocalDateTime.of(2020, 11, 18, 23, 15), ZoneOffset.ofHours(3));
        relay.setCreationDate(creationDate);
        when(relayRepository.findByCreationDate(any(), any()))
                .thenReturn(new PageImpl<>(List.of(relay)));

        List<com.relay.web.model.Relay> foundedRelayList =
                relayService.findByDateOfManufacture(LocalDate.of(2020, Month.NOVEMBER, 18));

        assertEquals(1, foundedRelayList.size());
        assertEquals(relay.getCreationDate(), foundedRelayList.get(0).getDateOfManufacture());
    }

    @Test
    void nonFindRelayByDate() {

        when(relayRepository.findByCreationDate(any(), any()))
                .thenReturn(new PageImpl<>(emptyList()));

        List<com.relay.web.model.Relay> foundedRelayList =
                relayService.findByDateOfManufacture(LocalDate.of(2020, Month.NOVEMBER, 18));

        assertEquals(0, foundedRelayList.size());
    }
    //
    // @Disabled
    // @Test
    // public void testFindRelayAfterDateOfManufacture() {
    //
    // Relay equalsRelay = new Relay();
    // equalsRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 5));
    // relayService.save(equalsRelay);
    //
    // Relay lessRelay = new Relay();
    // lessRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
    // relayService.save(lessRelay);
    //
    // Relay moreRelay = new Relay();
    // moreRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 7));
    // Relay savedMoreRelay = relayService.save(moreRelay);
    //
    // List<Relay> foundedRelayList = relayService
    // .findByDateOfManufactureAfter(LocalDate.of(2018, Month.JUNE, 6)).getContent();
    // assertEquals(1, foundedRelayList.size());
    // assertEquals(savedMoreRelay.getId(), foundedRelayList.get(0).getId());
    //
    // }
    //
    // @Disabled
    // @Test
    // public void testFindRelayBeforeDateOfManufacture() {
    //
    // Relay equalsRelay = new Relay();
    // equalsRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 5));
    // relayService.save(equalsRelay);
    //
    // Relay lessRelay = new Relay();
    // lessRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
    // relayService.save(lessRelay);
    //
    // Relay moreRelay = new Relay();
    // moreRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 7));
    // relayService.save(moreRelay);
    //
    // List<Relay> foundedRelayList = relayService
    // .findByDateOfManufactureBefore(LocalDate.of(2018, Month.JUNE, 6)).getContent();
    // assertEquals(1, foundedRelayList.size());
    // assertEquals(equalsRelay.getId(), foundedRelayList.get(0).getId());
    //
    // }
    //
    @Test
    void findAll() {

        Relay relay1 = Relay.builder().serialNumber("012345").build();
        Relay relay2 = Relay.builder().serialNumber("012345").build();
        Relay relay3 = Relay.builder().serialNumber("012345").build();
        Relay relay4 = Relay.builder().serialNumber("012345").build();
        List<Relay> relays = List.of(relay1, relay2, relay3, relay4);
        when(relayRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(relays));

        List<com.relay.web.model.Relay> relayList = relayService.findAll(PageRequest.of(0, 10));
        assertNotNull(relayList);
        assertEquals(4, relayList.size());

    }
    //
    // @Test
    // public void findRelayByStationName() {
    // Station monetnajaStation = Station.builder().build();
    // List<RelayEntity> relaysByMonetnajaStation = createRelaysByStation(4, monetnajaStation);
    //
    // when(relayRepository.findRelaysByStationName("Монетная")).thenReturn(relaysByMonetnajaStation);
    //
    // List<Relay> relays = relayService.findByStationName("Монетная");
    // assertNotNull(relays);
    // assertEquals(4, relays.size());
    // }

}
