package com.relay.core.service;

import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.core.model.storage.RelayCabinet;
import com.relay.core.model.storage.Stand;
import com.relay.core.model.storage.Warehouse;
import com.relay.db.entity.location.Location;
import com.relay.db.mappers.LocationMapper;
import com.relay.db.mappers.StorageMapper;
import com.relay.db.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StorageService {

    private final WarehouseRepository warehouseRepository;
    private final StandRepository standRepository;
    private final RelayCabinetRepository relayCabinetRepository;
    private final StationRepository stationRepository;
    private final TrackPointRepository trackPointRepository;
    private final CrossingRepository crossingRepository;
    private final StorageMapper storageMapper;
    private final LocationMapper locationMapper;

    // Warehouse operations
    public Warehouse saveWarehouse(@NonNull Warehouse model, @NonNull Long locationId) {
        Location location = findLocationById(locationId);
        com.relay.db.entity.storage.Warehouse entity = storageMapper.mapModelToWarehouseEntity(model);
        entity.setLocation(location);
        com.relay.db.entity.storage.Warehouse saved = warehouseRepository.save(entity);
        return storageMapper.mapWarehouseEntityToModel(saved);
    }

    public @Nullable Warehouse findWarehouseById(@NonNull Long id) {
        return warehouseRepository.findById(id)
                .map(storageMapper::mapWarehouseEntityToModel)
                .orElse(null);
    }

    public @Nullable Warehouse updateWarehouse(@NonNull Long id, @NonNull Warehouse model) {
        return warehouseRepository.findById(id)
                .map(warehouse -> {
                    warehouse.setName(model.name());
                    if (model.locationId() != null) {
                        Location location = findLocationById(model.locationId());
                        warehouse.setLocation(location);
                    }
                    return storageMapper.mapWarehouseEntityToModel(warehouseRepository.save(warehouse));
                })
                .orElse(null);
    }

    public @NonNull List<Warehouse> findAllWarehouses(@NonNull Pageable pageable) {
        var slice = warehouseRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(storageMapper::mapWarehouseEntityToModel).toList()
                : List.of();
    }

    public void deleteWarehouseById(@NonNull Long id) {
        warehouseRepository.deleteById(id);
    }

    // Stand operations
    public Stand saveStand(@NonNull Stand model, @NonNull Long locationId) {
        Location location = findLocationById(locationId);
        com.relay.db.entity.storage.Stand entity = storageMapper.mapModelToStandEntity(model);
        entity.setLocation(location);
        com.relay.db.entity.storage.Stand saved = standRepository.save(entity);
        return storageMapper.mapStandEntityToModel(saved);
    }

    public @Nullable Stand findStandById(@NonNull Long id) {
        return standRepository.findById(id)
                .map(storageMapper::mapStandEntityToModel)
                .orElse(null);
    }

    public @Nullable Stand updateStand(@NonNull Long id, @NonNull Stand model) {
        return standRepository.findById(id)
                .map(stand -> {
                    stand.setName(model.name());
                    if (model.locationId() != null) {
                        Location location = findLocationById(model.locationId());
                        stand.setLocation(location);
                    }
                    return storageMapper.mapStandEntityToModel(standRepository.save(stand));
                })
                .orElse(null);
    }

    public @NonNull List<Stand> findAllStands(@NonNull Pageable pageable) {
        var slice = standRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(storageMapper::mapStandEntityToModel).toList()
                : List.of();
    }

    public void deleteStandById(@NonNull Long id) {
        standRepository.deleteById(id);
    }

    // RelayCabinet operations
    public RelayCabinet saveRelayCabinet(@NonNull RelayCabinet model, @NonNull Long locationId) {
        Location location = findLocationById(locationId);
        com.relay.db.entity.storage.RelayCabinet entity = storageMapper.mapModelToRelayCabinetEntity(model);
        entity.setLocation(location);
        com.relay.db.entity.storage.RelayCabinet saved = relayCabinetRepository.save(entity);
        return storageMapper.mapRelayCabinetEntityToModel(saved);
    }

    public @Nullable RelayCabinet findRelayCabinetById(@NonNull Long id) {
        return relayCabinetRepository.findById(id)
                .map(storageMapper::mapRelayCabinetEntityToModel)
                .orElse(null);
    }

    public @Nullable RelayCabinet updateRelayCabinet(@NonNull Long id, @NonNull RelayCabinet model) {
        return relayCabinetRepository.findById(id)
                .map(cabinet -> {
                    cabinet.setName(model.name());
                    if (model.locationId() != null) {
                        Location location = findLocationById(model.locationId());
                        cabinet.setLocation(location);
                    }
                    return storageMapper.mapRelayCabinetEntityToModel(relayCabinetRepository.save(cabinet));
                })
                .orElse(null);
    }

    public @NonNull List<RelayCabinet> findAllRelayCabinets(@NonNull Pageable pageable) {
        var slice = relayCabinetRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(storageMapper::mapRelayCabinetEntityToModel).toList()
                : List.of();
    }

    public void deleteRelayCabinetById(@NonNull Long id) {
        relayCabinetRepository.deleteById(id);
    }

    private Location findLocationById(Long locationId) {
        return stationRepository.findById(locationId).map(Location.class::cast)
                .or(() -> trackPointRepository.findById(locationId).map(Location.class::cast))
                .or(() -> crossingRepository.findById(locationId).map(Location.class::cast))
                .orElseThrow(() -> new IllegalArgumentException("Location not found: " + locationId));
    }

    // Location finder methods
    public @Nullable Station findStationById(@NonNull Long id) {
        return stationRepository.findById(id)
                .map(locationMapper::mapStationEntityToModel)
                .orElse(null);
    }

    public @Nullable TrackPoint findTrackPointById(@NonNull Long id) {
        return trackPointRepository.findById(id)
                .map(locationMapper::mapTrackPointEntityToModel)
                .orElse(null);
    }

    public @Nullable Crossing findCrossingById(@NonNull Long id) {
        return crossingRepository.findById(id)
                .map(locationMapper::mapCrossingEntityToModel)
                .orElse(null);
    }
}
