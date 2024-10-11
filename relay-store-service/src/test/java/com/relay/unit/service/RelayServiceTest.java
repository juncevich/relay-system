package com.relay.unit.service;

import com.relay.core.mappers.RelayMapper;
import com.relay.core.service.RelayService;
import com.relay.db.entity.items.Relay;
import com.relay.db.repository.RelayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class RelayServiceTest {

    @Mock
    private RelayRepository relayRepository;

    @Mock
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
        relay.setCreatedAt(creationDate);
        when(relayRepository.findByCreationDate(any(), any()))
                .thenReturn(new PageImpl<>(List.of(relay)));

        List<com.relay.web.model.Relay> foundedRelayList =
                relayService.findByDateOfManufacture(LocalDate.of(2020, Month.NOVEMBER, 18));

        assertEquals(1, foundedRelayList.size());
        assertEquals(relay.getCreatedAt(), foundedRelayList.get(0).getCreatedAt());
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

        Relay       relay1 = Relay.builder().serialNumber("012345").build();
        Relay       relay2 = Relay.builder().serialNumber("012345").build();
        Relay       relay3 = Relay.builder().serialNumber("012345").build();
        Relay       relay4 = Relay.builder().serialNumber("012345").build();
        List<Relay> relays = List.of(relay1, relay2, relay3, relay4);
        when(relayRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(relays));

        List<com.relay.web.model.Relay> relayList = relayService.findAll(PageRequest.of(0, 10));
        assertNotNull(relayList);
        assertEquals(4, relayList.size());

    }


    @Test
    void findRelayById() {

        Relay relay = new Relay();
        relay.setId(1L);
        relay.setSerialNumber("12345");
        OffsetDateTime creationDate =
                OffsetDateTime.of(LocalDateTime.of(2020, 11, 18, 23, 15), ZoneOffset.ofHours(3));
        relay.setCreatedAt(creationDate);
        when(relayRepository.findById(anyLong()))
                .thenReturn(Optional.of(relay));

        com.relay.web.model.Relay foundedRelay = relayService.findOne(relay.getId());

        assertEquals("12345", foundedRelay.getSerialNumber());
        assertEquals(creationDate, foundedRelay.getCreatedAt());
    }

}
