package com.relay.db.repository;

import com.relay.core.exceptions.RelayNotFoundException;
import com.relay.core.model.Relay;
import com.relay.db.dao.RelayDao;
import com.relay.db.mappers.RelayMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RelayRepository {

    private final RelayDao relayDao;
    private final RelayMapper relayMapper;
    private final StorageRepository storageRepository;
    private final ZoneId applicationZoneId;

    public @NonNull Page<Relay> findAll(@NonNull Pageable pageable) {
        return relayDao.findAll(pageable)
                .map(relayMapper::mapEntityToModel);
    }

    public Optional<Relay> findById(@NonNull Long id) {
        return relayDao.findById(id)
                .map(relayMapper::mapEntityToModel);
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

    public Optional<Relay> findBySerialNumber(@NonNull String serialNumber) {
        return relayDao.findBySerialNumber(serialNumber)
                .map(relayMapper::mapEntityToModel);
    }

    public @NonNull Page<Relay> findByCreationDate(@NonNull LocalDate date,
                                                   @NonNull Pageable pageable) {
        var rangeStart = date.atStartOfDay(applicationZoneId).toOffsetDateTime();
        var rangeEnd = date.plusDays(1).atStartOfDay(applicationZoneId).toOffsetDateTime();
        return relayDao.findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(rangeStart, rangeEnd, pageable)
                .map(relayMapper::mapEntityToModel);
    }

    public @NonNull Page<Relay> findByLastCheckDate(@NonNull LocalDate date,
                                                    @NonNull Pageable pageable) {
        var rangeStart = date.atStartOfDay(applicationZoneId).toOffsetDateTime();
        var rangeEnd = date.plusDays(1).atStartOfDay(applicationZoneId).toOffsetDateTime();
        return relayDao.findByLastCheckDateGreaterThanEqualAndLastCheckDateLessThan(rangeStart, rangeEnd, pageable)
                .map(relayMapper::mapEntityToModel);
    }

    /**
     * Helper method to get entity for relationship wiring.
     * Throws RelayNotFoundException if relay is not found.
     */
    public com.relay.db.entity.items.@NonNull Relay findRelayEntityById(@NonNull Long relayId) {
        return relayDao.findById(relayId)
                .orElseThrow(() -> new RelayNotFoundException(relayId));
    }
}
