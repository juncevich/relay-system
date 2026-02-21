package com.relay.core.service;

import com.relay.core.exceptions.RelayNotFoundException;
import com.relay.core.model.history.RelayMovement;
import com.relay.db.repository.RelayMovementRepository;
import com.relay.db.repository.RelayRepository;
import com.relay.db.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RelayMovementService {

    private final RelayMovementRepository relayMovementRepository;
    private final RelayRepository relayRepository;
    private final StorageRepository storageRepository;

    @Transactional(readOnly = true)
    public @NonNull Page<RelayMovement> findAll(@NonNull Pageable pageable) {
        return relayMovementRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public @NonNull RelayMovement findById(@NonNull Long id) {
        var movement = relayMovementRepository.findById(id);
        if (movement == null) {
            throw new com.relay.core.exceptions.EntityNotFoundException("RelayMovement", id);
        }
        return movement;
    }

    @Transactional(readOnly = true)
    public @NonNull Page<RelayMovement> findByRelayId(@NonNull Long relayId, @NonNull Pageable pageable) {
        return relayMovementRepository.findByRelayId(relayId, pageable);
    }

    public RelayMovement save(@NonNull Long relayId,
                                   @NonNull Long fromStorageId,
                                   @NonNull Long toStorageId) {
        // Validate entities exist
        var relay = relayRepository.findById(relayId)
                .orElseThrow(() -> new RelayNotFoundException(relayId));
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
