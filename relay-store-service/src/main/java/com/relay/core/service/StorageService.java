package com.relay.core.service;

import com.relay.core.model.location.CrossingModel;
import com.relay.core.model.location.StationModel;
import com.relay.core.model.location.TrackPointModel;
import com.relay.core.model.storage.RelayCabinetModel;
import com.relay.core.model.storage.StandModel;
import com.relay.core.model.storage.WarehouseModel;
import com.relay.db.entity.location.Location;
import com.relay.db.entity.storage.RelayCabinet;
import com.relay.db.entity.storage.Stand;
import com.relay.db.entity.storage.Warehouse;
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
    public WarehouseModel saveWarehouse(@NonNull WarehouseModel model, @NonNull Long locationId) {
        Location location = findLocationById(locationId);
        Warehouse entity = storageMapper.mapModelToWarehouseEntity(model);
        entity.setLocation(location);
        Warehouse saved = warehouseRepository.save(entity);
        return storageMapper.mapWarehouseEntityToModel(saved);
    }

    public @Nullable WarehouseModel findWarehouseById(@NonNull Long id) {
        return warehouseRepository.findById(id)
                .map(storageMapper::mapWarehouseEntityToModel)
                .orElse(null);
    }

    public @Nullable WarehouseModel updateWarehouse(@NonNull Long id, @NonNull WarehouseModel model) {
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

    public @NonNull List<WarehouseModel> findAllWarehouses(@NonNull Pageable pageable) {
        var slice = warehouseRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(storageMapper::mapWarehouseEntityToModel).toList()
                : List.of();
    }

    public void deleteWarehouseById(@NonNull Long id) {
        warehouseRepository.deleteById(id);
    }

    // Stand operations
    public StandModel saveStand(@NonNull StandModel model, @NonNull Long locationId) {
        Location location = findLocationById(locationId);
        Stand entity = storageMapper.mapModelToStandEntity(model);
        entity.setLocation(location);
        Stand saved = standRepository.save(entity);
        return storageMapper.mapStandEntityToModel(saved);
    }

    public @Nullable StandModel findStandById(@NonNull Long id) {
        return standRepository.findById(id)
                .map(storageMapper::mapStandEntityToModel)
                .orElse(null);
    }

    public @Nullable StandModel updateStand(@NonNull Long id, @NonNull StandModel model) {
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

    public @NonNull List<StandModel> findAllStands(@NonNull Pageable pageable) {
        var slice = standRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(storageMapper::mapStandEntityToModel).toList()
                : List.of();
    }

    public void deleteStandById(@NonNull Long id) {
        standRepository.deleteById(id);
    }

    // RelayCabinet operations
    public RelayCabinetModel saveRelayCabinet(@NonNull RelayCabinetModel model, @NonNull Long locationId) {
        Location location = findLocationById(locationId);
        RelayCabinet entity = storageMapper.mapModelToRelayCabinetEntity(model);
        entity.setLocation(location);
        RelayCabinet saved = relayCabinetRepository.save(entity);
        return storageMapper.mapRelayCabinetEntityToModel(saved);
    }

    public @Nullable RelayCabinetModel findRelayCabinetById(@NonNull Long id) {
        return relayCabinetRepository.findById(id)
                .map(storageMapper::mapRelayCabinetEntityToModel)
                .orElse(null);
    }

    public @Nullable RelayCabinetModel updateRelayCabinet(@NonNull Long id, @NonNull RelayCabinetModel model) {
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

    public @NonNull List<RelayCabinetModel> findAllRelayCabinets(@NonNull Pageable pageable) {
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
    public @Nullable StationModel findStationById(@NonNull Long id) {
        return stationRepository.findById(id)
                .map(locationMapper::mapStationEntityToModel)
                .orElse(null);
    }

    public @Nullable TrackPointModel findTrackPointById(@NonNull Long id) {
        return trackPointRepository.findById(id)
                .map(locationMapper::mapTrackPointEntityToModel)
                .orElse(null);
    }

    public @Nullable CrossingModel findCrossingById(@NonNull Long id) {
        return crossingRepository.findById(id)
                .map(locationMapper::mapCrossingEntityToModel)
                .orElse(null);
    }
}
