package com.relay.core.repository;

import com.relay.core.model.storage.RelayCabinet;
import com.relay.core.model.storage.Stand;
import com.relay.core.model.storage.Warehouse;
import com.relay.db.dao.RelayCabinetDao;
import com.relay.db.dao.StandDao;
import com.relay.db.dao.WarehouseDao;
import com.relay.db.entity.storage.Storage;
import com.relay.db.mappers.StorageMapper;
import com.relay.web.exceptions.StorageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StorageRepository {

    private final WarehouseDao warehouseDao;
    private final StandDao standDao;
    private final RelayCabinetDao relayCabinetDao;
    private final StorageMapper storageMapper;

    // Warehouse operations
    public Warehouse saveWarehouse(@NonNull Warehouse model) {
        var entity = storageMapper.mapModelToWarehouseEntity(model);
        var saved = warehouseDao.save(entity);
        return storageMapper.mapWarehouseEntityToModel(saved);
    }

    public Optional<Warehouse> findWarehouseById(@NonNull Long id) {
        return warehouseDao.findById(id)
                .map(storageMapper::mapWarehouseEntityToModel);
    }

    public @NonNull List<Warehouse> findAllWarehouses(@NonNull Pageable pageable) {
        var slice = warehouseDao.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream()
                .map(storageMapper::mapWarehouseEntityToModel).toList()
                : List.of();
    }

    public void deleteWarehouseById(@NonNull Long id) {
        warehouseDao.deleteById(id);
    }

    // Stand operations
    public Stand saveStand(@NonNull Stand model) {
        var entity = storageMapper.mapModelToStandEntity(model);
        var saved = standDao.save(entity);
        return storageMapper.mapStandEntityToModel(saved);
    }

    public Optional<Stand> findStandById(@NonNull Long id) {
        return standDao.findById(id)
                .map(storageMapper::mapStandEntityToModel);
    }

    public @NonNull List<Stand> findAllStands(@NonNull Pageable pageable) {
        var slice = standDao.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream()
                .map(storageMapper::mapStandEntityToModel).toList()
                : List.of();
    }

    public void deleteStandById(@NonNull Long id) {
        standDao.deleteById(id);
    }

    // RelayCabinet operations
    public RelayCabinet saveRelayCabinet(@NonNull RelayCabinet model) {
        var entity = storageMapper.mapModelToRelayCabinetEntity(model);
        var saved = relayCabinetDao.save(entity);
        return storageMapper.mapRelayCabinetEntityToModel(saved);
    }

    public Optional<RelayCabinet> findRelayCabinetById(@NonNull Long id) {
        return relayCabinetDao.findById(id)
                .map(storageMapper::mapRelayCabinetEntityToModel);
    }

    public @NonNull List<RelayCabinet> findAllRelayCabinets(@NonNull Pageable pageable) {
        var slice = relayCabinetDao.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream()
                .map(storageMapper::mapRelayCabinetEntityToModel).toList()
                : List.of();
    }

    public void deleteRelayCabinetById(@NonNull Long id) {
        relayCabinetDao.deleteById(id);
    }

    /**
     * Polymorphic finder - returns entity for relationship wiring.
     * Searches across all storage types (Warehouse, Stand, RelayCabinet).
     * Throws StorageNotFoundException if storage is not found.
     */
    public com.relay.db.entity.storage.@NonNull Storage findStorageEntityById(@NonNull Long storageId) {
        return warehouseDao.findById(storageId).map(s -> (Storage) s)
                .or(() -> standDao.findById(storageId).map(s -> (Storage) s))
                .or(() -> relayCabinetDao.findById(storageId).map(s -> (Storage) s))
                .orElseThrow(() -> new StorageNotFoundException(storageId));
    }
}
