package com.relay.core.repository;

import com.relay.core.model.Relay;
import com.relay.db.dao.RelayDao;
import com.relay.db.mappers.RelayMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RelayRepository {

    private final RelayDao relayDao;
    private final RelayMapper relayMapper;
    private final StorageRepository storageRepository;

    public @NonNull List<Relay> findAll(@NonNull Pageable pageable) {
        var slice = relayDao.findAll(pageable);
        return slice.hasContent()
                ? relayMapper.mapEntityToModel(slice.getContent())
                : List.of();
    }

    public @Nullable Relay findById(@NonNull Long id) {
        return relayDao.findById(id)
                .map(relayMapper::mapEntityToModel)
                .orElse(null);
    }

    public Relay save(@NonNull Relay model) {
        var entity = relayMapper.mapModelToEntity(model);

        // Set storage relationship if storageId is provided
        if (model.storageId() != null) {
            var storageEntity = storageRepository.findStorageEntityById(model.storageId());
            entity.setStorage(storageEntity);
        }

        var saved = relayDao.save(entity);
        return relayMapper.mapEntityToModel(saved);
    }

    public void deleteById(@NonNull Long id) {
        relayDao.deleteById(id);
    }

    public @Nullable Relay findBySerialNumber(@NonNull String serialNumber) {
        var entity = relayDao.findBySerialNumber(serialNumber);
        return entity != null ? relayMapper.mapEntityToModel(entity) : null;
    }

    public @NonNull List<Relay> findByCreationDate(@NonNull LocalDate date,
                                                   @NonNull Pageable pageable) {
        var page = relayDao.findByCreationDate(date, pageable);
        return relayMapper.mapEntityToModel(page.getContent());
    }

    public @NonNull List<Relay> findByLastCheckDate(@NonNull LocalDate date,
                                                    @NonNull Pageable pageable) {
        var page = relayDao.findByLastCheckDate(date, pageable);
        return relayMapper.mapEntityToModel(page.getContent());
    }

    // Helper method to get entity for relationship wiring
    public com.relay.db.entity.items.@NonNull Relay findRelayEntityById(@NonNull Long relayId) {
        return relayDao.findById(relayId)
                .orElseThrow(() -> new IllegalArgumentException("Relay not found: " + relayId));
    }
}
