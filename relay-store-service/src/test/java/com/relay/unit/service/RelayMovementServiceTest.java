package com.relay.unit.service;

import com.relay.core.exceptions.InvalidBusinessStateException;
import com.relay.core.exceptions.RelayNotFoundException;
import com.relay.core.model.Relay;
import com.relay.core.model.history.RelayMovement;
import com.relay.core.service.RelayMovementService;
import com.relay.db.repository.RelayMovementRepository;
import com.relay.db.repository.RelayRepository;
import com.relay.db.repository.StorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
class RelayMovementServiceTest {

    @Mock
    private RelayMovementRepository relayMovementRepository;
    @Mock
    private RelayRepository relayRepository;
    @Mock
    private StorageRepository storageRepository;

    private RelayMovementService relayMovementService;

    @BeforeEach
    void setUp() {
        relayMovementService = new RelayMovementService(
                relayMovementRepository,
                relayRepository,
                storageRepository
        );
    }

    @Test
    void shouldThrowWhenMovingWithinSameStorage() {
        Long relayId = 100L;
        Long storageId = 200L;

        assertThrows(InvalidBusinessStateException.class,
                () -> relayMovementService.save(relayId, storageId, storageId));
        verifyNoInteractions(relayRepository, storageRepository, relayMovementRepository);
    }

    @Test
    void shouldThrowWhenRelayNotInSourceStorage() {
        Long relayId = 100L;
        Long relayStorageId = 200L;
        Long sourceStorageId = 201L;
        Long targetStorageId = 202L;
        Relay relay = new Relay(relayId, "012345", null, null, null, 0, relayStorageId, null);
        when(relayRepository.findById(relayId)).thenReturn(Optional.of(relay));

        assertThrows(InvalidBusinessStateException.class,
                () -> relayMovementService.save(relayId, sourceStorageId, targetStorageId));
        verifyNoInteractions(storageRepository, relayMovementRepository);
    }

    @Test
    void shouldSaveMovementAndUpdateRelayStorage() {
        Long relayId = 100L;
        Long sourceStorageId = 200L;
        Long targetStorageId = 201L;
        Relay relay = new Relay(relayId, "012345", null, OffsetDateTime.now(), null, 0, sourceStorageId, null);

        when(relayRepository.findById(relayId)).thenReturn(Optional.of(relay));
        when(relayMovementRepository.save(any(RelayMovement.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RelayMovement result = relayMovementService.save(relayId, sourceStorageId, targetStorageId);

        ArgumentCaptor<Relay> relayCaptor = ArgumentCaptor.forClass(Relay.class);
        verify(relayRepository).save(relayCaptor.capture());
        assertThat(relayCaptor.getValue().storageId()).isEqualTo(targetStorageId);

        assertThat(result.relayId()).isEqualTo(relayId);
        assertThat(result.fromStorageId()).isEqualTo(sourceStorageId);
        assertThat(result.toStorageId()).isEqualTo(targetStorageId);
    }

    @Test
    void shouldThrowWhenRelayNotFound() {
        when(relayRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RelayNotFoundException.class,
                () -> relayMovementService.save(999L, 100L, 101L));
    }
}
