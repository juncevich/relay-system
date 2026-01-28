package com.relay.core.service;

import com.relay.db.entity.storage.Storage;
import com.relay.db.mappers.RelayMapper;
import com.relay.db.repository.RelayCabinetRepository;
import com.relay.db.repository.RelayRepository;
import com.relay.db.repository.StandRepository;
import com.relay.db.repository.WarehouseRepository;
import com.relay.web.model.Relay;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RelayService {

    private final RelayRepository relayRepository;
    private final WarehouseRepository warehouseRepository;
    private final StandRepository standRepository;
    private final RelayCabinetRepository relayCabinetRepository;
    private final RelayMapper relayMapper;

    public @NonNull List<Relay> findAll(@NonNull Pageable pageable) {
        var relaySlice = relayRepository.findAll(pageable);
        return relaySlice.hasContent()
                ? relaySlice.getContent().stream().map(relayMapper::mapEntityToModel).toList()
                : emptyList();
    }

    public @Nullable Relay findById(@NonNull Long id) {
        return relayRepository.findById(id)
                .map(relayMapper::mapEntityToModel)
                .orElse(null);
    }

    public Relay save(@NonNull Relay relay) {
        var entity = relayMapper.mapModelToEntity(relay);
        var savedEntity = relayRepository.save(entity);
        return relayMapper.mapEntityToModel(savedEntity);
    }

    public Relay save(@NonNull Relay relay, @NonNull Long storageId) {
        Storage storage = findStorageById(storageId);
        var entity = relayMapper.mapModelToEntity(relay);
        entity.setStorage(storage);
        var savedEntity = relayRepository.save(entity);
        return relayMapper.mapEntityToModel(savedEntity);
    }

    public @Nullable Relay update(@NonNull Long id, @NonNull Relay relay, @Nullable Long storageId) {
        return relayRepository.findById(id)
                .map(entity -> {
                    entity.setSerialNumber(relay.getSerialNumber());
                    if (relay.getCreatedAt() != null) {
                        entity.setCreatedAt(relay.getCreatedAt());
                    }
                    if (relay.getVerificationDate() != null) {
                        entity.setLastCheckDate(relay.getVerificationDate());
                    }
                    if (storageId != null) {
                        Storage storage = findStorageById(storageId);
                        entity.setStorage(storage);
                    }
                    return relayMapper.mapEntityToModel(relayRepository.save(entity));
                })
                .orElse(null);
    }

    public void deleteById(@NonNull Long id) {
        relayRepository.deleteById(id);
    }

    public @Nullable Relay findBySerialNumber(@NonNull String serialNumber) {
        var entity = relayRepository.findBySerialNumber(serialNumber);
        return entity != null ? relayMapper.mapEntityToModel(entity) : null;
    }

    public @NonNull List<Relay> findByCreationDate(@NonNull LocalDate date, @NonNull Pageable pageable) {
        log.info("Finding relays with creation date {}", date);
        var relays = relayRepository.findByCreationDate(date, pageable);
        return relayMapper.mapEntityToModel(relays.getContent());
    }

    public @NonNull List<Relay> findByLastCheckDate(@NonNull LocalDate date, @NonNull Pageable pageable) {
        log.info("Finding relays with last check date {}", date);
        var relays = relayRepository.findByLastCheckDate(date, pageable);
        return relayMapper.mapEntityToModel(relays.getContent());
    }

    private Storage findStorageById(Long storageId) {
        return warehouseRepository.findById(storageId).map(s -> (Storage) s)
                .or(() -> standRepository.findById(storageId).map(s -> (Storage) s))
                .or(() -> relayCabinetRepository.findById(storageId).map(s -> (Storage) s))
                .orElseThrow(() -> new IllegalArgumentException("Storage not found: " + storageId));
    }
}
