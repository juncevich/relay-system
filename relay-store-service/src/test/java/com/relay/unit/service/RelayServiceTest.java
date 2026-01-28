package com.relay.unit.service;

import com.relay.core.service.RelayService;
import com.relay.db.entity.items.Relay;
import com.relay.db.mappers.RelayMapper;
import com.relay.db.repository.RelayCabinetRepository;
import com.relay.db.repository.RelayRepository;
import com.relay.db.repository.StandRepository;
import com.relay.db.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.*;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class RelayServiceTest {

    @MockitoBean
    private RelayRepository relayRepository;

    @MockitoBean
    private WarehouseRepository warehouseRepository;

    @MockitoBean
    private StandRepository standRepository;

    @MockitoBean
    private RelayCabinetRepository relayCabinetRepository;

    @Mock
    private RelayService relayService;

    @BeforeEach
    void setUp() {
        relayService = new RelayService(
                relayRepository,
                warehouseRepository,
                standRepository,
                relayCabinetRepository,
                RelayMapper.INSTANCE
        );
    }

    @Test
    void findRelayByCreationDate() {
        Relay relay = new Relay();
        relay.setId(1L);
        relay.setSerialNumber("12345");
        OffsetDateTime creationDate =
                OffsetDateTime.of(LocalDateTime.of(2020, 11, 18, 23, 15), ZoneOffset.ofHours(3));
        relay.setCreatedAt(creationDate);
        when(relayRepository.findByCreationDate(any(), any()))
                .thenReturn(new PageImpl<>(List.of(relay)));

        List<com.relay.web.model.Relay> foundedRelayList =
                relayService.findByCreationDate(LocalDate.of(2020, Month.NOVEMBER, 18), PageRequest.of(0, 10));

        assertEquals(1, foundedRelayList.size());
        assertEquals(relay.getCreatedAt(), foundedRelayList.get(0).getCreatedAt());
    }

    @Test
    void nonFindRelayByCreationDate() {
        when(relayRepository.findByCreationDate(any(), any()))
                .thenReturn(new PageImpl<>(emptyList()));

        List<com.relay.web.model.Relay> foundedRelayList =
                relayService.findByCreationDate(LocalDate.of(2020, Month.NOVEMBER, 18), PageRequest.of(0, 10));

        assertEquals(0, foundedRelayList.size());
    }

    @Test
    void findAll() {
        Relay relay1 = Relay.builder().serialNumber("012345").build();
        Relay relay2 = Relay.builder().serialNumber("012345").build();
        Relay relay3 = Relay.builder().serialNumber("012345").build();
        Relay relay4 = Relay.builder().serialNumber("012345").build();
        List<Relay> relays = List.of(relay1, relay2, relay3, relay4);
        when(relayRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(relays));

        List<com.relay.web.model.Relay> relayList = relayService.findAll(PageRequest.of(0, 10));
        assertThat(relayList)
                .isNotNull()
                .hasSize(4);
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

        com.relay.web.model.Relay foundedRelay = relayService.findById(relay.getId());
        assertEquals(relay.getSerialNumber(), foundedRelay.getSerialNumber());
        assertThat(foundedRelay.getSerialNumber()).isEqualTo("12345");
        assertThat(foundedRelay.getCreatedAt()).isEqualTo(creationDate);
    }
}
