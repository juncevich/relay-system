package com.relay.core.service;

import com.relay.core.model.history.RelayMovement;
import com.relay.db.mappers.RelayMovementMapper;
import com.relay.db.repository.RelayMovementRepository;
import com.relay.db.repository.RelayRepository;
import com.relay.db.repository.StorageRepository;
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
    private final RelayMovementMapper relayMovementMapper;

    public @NonNull List<RelayMovement> findAll(@NonNull Pageable pageable) {
        var slice = relayMovementRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(relayMovementMapper::mapEntityToModel).toList()
                : List.of();
    }

    public @Nullable RelayMovement findById(@NonNull Long id) {
        return relayMovementRepository.findById(id)
                .map(relayMovementMapper::mapEntityToModel)
                .orElse(null);
    }

    public @NonNull List<RelayMovement> findByRelayId(@NonNull Long relayId, @NonNull Pageable pageable) {
        return relayMovementRepository.findByRelayId(relayId, pageable)
                .getContent()
                .stream()
                .map(relayMovementMapper::mapEntityToModel)
                .toList();
    }

    public RelayMovement save(@NonNull Long relayId,
                                   @NonNull Long fromStorageId,
                                   @NonNull Long toStorageId) {
        com.relay.db.entity.items.Relay relay = relayRepository.findById(relayId)
                .orElseThrow(() -> new IllegalArgumentException("Relay not found: " + relayId));
        com.relay.db.entity.storage.Storage fromStorage = storageRepository.findById(fromStorageId)
                .orElseThrow(() -> new IllegalArgumentException("From Storage not found: " + fromStorageId));
        com.relay.db.entity.storage.Storage toStorage = storageRepository.findById(toStorageId)
                .orElseThrow(() -> new IllegalArgumentException("To Storage not found: " + toStorageId));

        com.relay.db.entity.history.RelayMovement entity = new com.relay.db.entity.history.RelayMovement();
        entity.setRelay(relay);
        entity.setFromStorage(fromStorage);
        entity.setToStorage(toStorage);
        entity.setMovedAt(OffsetDateTime.now());

        relay.setStorage(toStorage);
        relayRepository.save(relay);

        com.relay.db.entity.history.RelayMovement saved = relayMovementRepository.save(entity);
        return relayMovementMapper.mapEntityToModel(saved);
    }

    public void deleteById(@NonNull Long id) {
        relayMovementRepository.deleteById(id);
    }
}