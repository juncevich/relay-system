package com.relay.core.service;

import com.relay.core.model.Relay;
import com.relay.core.repository.RelayRepository;
import com.relay.core.repository.StorageRepository;
import com.relay.web.exceptions.RelayNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RelayService {

    private final RelayRepository relayRepository;
    private final StorageRepository storageRepository;

    public @NonNull List<Relay> findAll(@NonNull Pageable pageable) {
        return relayRepository.findAll(pageable);
    }

    public Optional<Relay> findById(@NonNull Long id) {
        return relayRepository.findById(id);
    }

    public Relay save(@NonNull Relay relay) {
        return relayRepository.save(relay);
    }

    public Relay save(@NonNull Relay relay, @NonNull Long storageId) {
        // Validate storage exists
        storageRepository.findStorageEntityById(storageId);

        // Create relay model with storageId
        var relayWithStorage = new Relay(
                relay.id(),
                relay.serialNumber(),
                relay.relayType(),
                relay.createdAt(),
                relay.lastCheckDate(),
                relay.placeNumber(),
                storageId,
                relay.shelfId()
        );

        return relayRepository.save(relayWithStorage);
    }

    public Relay update(@NonNull Long id, @NonNull Relay relay, @Nullable Long storageId) {
        var existing = relayRepository.findById(id)
                .orElseThrow(() -> new RelayNotFoundException(id));

        // Build updated relay
        var updated = new Relay(
                id,
                relay.serialNumber(),
                existing.relayType(), // Keep existing if not provided
                relay.createdAt() != null ? relay.createdAt() : existing.createdAt(),
                relay.lastCheckDate() != null ? relay.lastCheckDate() : existing.lastCheckDate(),
                existing.placeNumber(),
                storageId != null ? storageId : existing.storageId(),
                existing.shelfId()
        );

        return relayRepository.save(updated);
    }

    public void deleteById(@NonNull Long id) {
        relayRepository.deleteById(id);
    }

    public Optional<Relay> findBySerialNumber(@NonNull String serialNumber) {
        return relayRepository.findBySerialNumber(serialNumber);
    }

    public @NonNull List<Relay> findByCreationDate(@NonNull LocalDate date, @NonNull Pageable pageable) {
        log.info("Finding relays with creation date {}", date);
        return relayRepository.findByCreationDate(date, pageable);
    }

    public @NonNull List<Relay> findByLastCheckDate(@NonNull LocalDate date, @NonNull Pageable pageable) {
        log.info("Finding relays with last check date {}", date);
        return relayRepository.findByLastCheckDate(date, pageable);
    }
}
