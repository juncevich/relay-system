package com.relay.unit.service;

import com.relay.core.service.RelayService;
import com.relay.db.repository.RelayRepository;
import com.relay.db.repository.StorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.*;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
class RelayServiceTest {

    @Mock
    private RelayRepository relayRepository;

    @Mock
    private StorageRepository storageRepository;

    private RelayService relayService;

    @BeforeEach
    void setUp() {
        relayService = new RelayService(
                relayRepository,
                storageRepository
        );
    }

    @Test
    void findRelayByCreationDate() {
        OffsetDateTime creationDate =
                OffsetDateTime.of(LocalDateTime.of(2020, 11, 18, 23, 15), ZoneOffset.ofHours(3));
        com.relay.core.model.Relay relay = new com.relay.core.model.Relay(
                1L,
                "12345",
                null,
                creationDate,
                null,
                0,
                null,
                null
        );
        when(relayRepository.findByCreationDate(any(), any()))
                .thenReturn(new PageImpl<>(List.of(relay)));

        Page<com.relay.core.model.Relay> foundedRelayPage =
                relayService.findByCreationDate(LocalDate.of(2020, Month.NOVEMBER, 18), PageRequest.of(0, 10));

        assertEquals(1, foundedRelayPage.getTotalElements());
        assertEquals(relay.createdAt(), foundedRelayPage.getContent().get(0).createdAt());
    }

    @Test
    void nonFindRelayByCreationDate() {
        when(relayRepository.findByCreationDate(any(), any()))
                .thenReturn(new PageImpl<>(emptyList()));

        Page<com.relay.core.model.Relay> foundedRelayPage =
                relayService.findByCreationDate(LocalDate.of(2020, Month.NOVEMBER, 18), PageRequest.of(0, 10));

        assertEquals(0, foundedRelayPage.getTotalElements());
    }

    @Test
    void findAll() {
        com.relay.core.model.Relay relay1 = new com.relay.core.model.Relay(null, "012345", null, null, null, 0, null, null);
        com.relay.core.model.Relay relay2 = new com.relay.core.model.Relay(null, "012346", null, null, null, 0, null, null);
        com.relay.core.model.Relay relay3 = new com.relay.core.model.Relay(null, "012347", null, null, null, 0, null, null);
        com.relay.core.model.Relay relay4 = new com.relay.core.model.Relay(null, "012348", null, null, null, 0, null, null);
        List<com.relay.core.model.Relay> relays = List.of(relay1, relay2, relay3, relay4);
        when(relayRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(relays));

        Page<com.relay.core.model.Relay> relayPage = relayService.findAll(PageRequest.of(0, 10));
        assertThat(relayPage.getContent())
                .isNotNull()
                .hasSize(4);
    }

    @Test
    void findRelayById() {
        OffsetDateTime creationDate =
                OffsetDateTime.of(LocalDateTime.of(2020, 11, 18, 23, 15), ZoneOffset.ofHours(3));
        com.relay.core.model.Relay relay = new com.relay.core.model.Relay(
                1L,
                "12345",
                null,
                creationDate,
                null,
                0,
                null,
                null
        );
        when(relayRepository.findById(anyLong()))
                .thenReturn(Optional.of(relay));

        var foundedRelayOpt = relayService.findById(relay.id());
        assertThat(foundedRelayOpt).isPresent();

        com.relay.core.model.Relay foundedRelay = foundedRelayOpt.get();
        assertEquals(relay.serialNumber(), foundedRelay.serialNumber());
        assertThat(foundedRelay.serialNumber()).isEqualTo("12345");
        assertThat(foundedRelay.createdAt()).isEqualTo(creationDate);
    }
}
