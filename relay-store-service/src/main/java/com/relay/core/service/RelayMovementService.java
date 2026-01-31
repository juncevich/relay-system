package com.relay.core.service;

import com.relay.core.model.history.RelayMovement;
import com.relay.core.repository.RelayMovementRepository;
import com.relay.core.repository.RelayRepository;
import com.relay.core.repository.StorageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RelayMovementService {

    private final RelayMovementRepository relayMovementRepository;
    private final RelayRepository relayRepository;
    private final StorageRepository storageRepository;

    public @NonNull List<RelayMovement> findAll(@NonNull Pageable pageable) {
        return relayMovementRepository.findAll(pageable);
    }

    public @Nullable RelayMovement findById(@NonNull Long id) {
        return relayMovementRepository.findById(id);
    }

    public @NonNull List<RelayMovement> findByRelayId(@NonNull Long relayId, @NonNull Pageable pageable) {
        return relayMovementRepository.findByRelayId(relayId, pageable);
    }

    public RelayMovement save(@NonNull Long relayId,
                                   @NonNull Long fromStorageId,
                                   @NonNull Long toStorageId) {
        // Validate entities exist
        var relay = relayRepository.findById(relayId);
        if (relay == null) {
            throw new IllegalArgumentException("Relay not found: " + relayId);
        }
        storageRepository.findStorageEntityById(fromStorageId);
        storageRepository.findStorageEntityById(toStorageId);

        // Create movement model
        var movement = new RelayMovement(
                null,
                relayId,
                relay.serialNumber(),
                fromStorageId,
                toStorageId,
                OffsetDateTime.now()
        );

        // Save movement
        var savedMovement = relayMovementRepository.save(movement);

        // Update relay's storage (create updated relay model)
        var updatedRelay = new com.relay.core.model.Relay(
                relay.id(),
                relay.serialNumber(),
                relay.relayType(),
                relay.createdAt(),
                relay.lastCheckDate(),
                relay.placeNumber(),
                toStorageId,  // Update to new storage
                relay.shelfId()
        );
        relayRepository.save(updatedRelay);

        return savedMovement;
    }

    public void deleteById(@NonNull Long id) {
        relayMovementRepository.deleteById(id);
    }
}
