package com.relay.db.repository;

import com.relay.core.model.history.RelayMovement;
import com.relay.db.dao.RelayMovementDao;
import com.relay.db.mappers.RelayMovementMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RelayMovementRepository {

    private final RelayMovementDao relayMovementDao;
    private final RelayMovementMapper relayMovementMapper;

    public @NonNull List<RelayMovement> findAll(@NonNull Pageable pageable) {
        var slice = relayMovementDao.findAll(pageable);
        return slice.hasContent()
                ? relayMovementMapper.mapEntityToModel(slice.getContent())
                : List.of();
    }

    public @Nullable RelayMovement findById(@NonNull Long id) {
        return relayMovementDao.findById(id)
                .map(relayMovementMapper::mapEntityToModel)
                .orElse(null);
    }

    public RelayMovement save(@NonNull RelayMovement model) {
        var entity = relayMovementMapper.mapModelToEntity(model);
        var saved = relayMovementDao.save(entity);
        return relayMovementMapper.mapEntityToModel(saved);
    }

    public void deleteById(@NonNull Long id) {
        relayMovementDao.deleteById(id);
    }

    public @NonNull List<RelayMovement> findByRelayId(@NonNull Long relayId,
                                                      @NonNull Pageable pageable) {
        var page = relayMovementDao.findByRelayId(relayId, pageable);
        return relayMovementMapper.mapEntityToModel(page.getContent());
    }

    public @NonNull List<RelayMovement> findByFromStorageId(@NonNull Long storageId) {
        var list = relayMovementDao.findByFromStorageId(storageId);
        return relayMovementMapper.mapEntityToModel(list);
    }

    public @NonNull List<RelayMovement> findByToStorageId(@NonNull Long storageId) {
        var list = relayMovementDao.findByToStorageId(storageId);
        return relayMovementMapper.mapEntityToModel(list);
    }
}
