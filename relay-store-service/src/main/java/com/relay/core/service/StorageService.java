package com.relay.core.service;

import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.core.model.storage.RelayCabinet;
import com.relay.core.model.storage.Stand;
import com.relay.core.model.storage.Warehouse;
import com.relay.core.repository.LocationRepository;
import com.relay.core.repository.StorageRepository;
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

    private final StorageRepository storageRepository;
    private final LocationRepository locationRepository;

    // Warehouse operations
    public Warehouse saveWarehouse(@NonNull Warehouse model, @NonNull Long locationId) {
        // Validate location exists
        locationRepository.findLocationEntityById(locationId);

        // Create warehouse with locationId
        var warehouse = new Warehouse(null, model.name(), locationId);
        return storageRepository.saveWarehouse(warehouse);
    }

    public @Nullable Warehouse findWarehouseById(@NonNull Long id) {
        return storageRepository.findWarehouseById(id);
    }

    public @Nullable Warehouse updateWarehouse(@NonNull Long id, @NonNull Warehouse model) {
        var existing = storageRepository.findWarehouseById(id);
        if (existing == null) {
            return null;
        }

        Long locationId = model.locationId() != null ? model.locationId() : existing.locationId();
        if (locationId != null) {
            locationRepository.findLocationEntityById(locationId);
        }

        var updated = new Warehouse(id, model.name(), locationId);
        return storageRepository.saveWarehouse(updated);
    }

    public @NonNull List<Warehouse> findAllWarehouses(@NonNull Pageable pageable) {
        return storageRepository.findAllWarehouses(pageable);
    }

    public void deleteWarehouseById(@NonNull Long id) {
        storageRepository.deleteWarehouseById(id);
    }

    // Stand operations
    public Stand saveStand(@NonNull Stand model, @NonNull Long locationId) {
        locationRepository.findLocationEntityById(locationId);

        var stand = new Stand(null, model.name(), locationId);
        return storageRepository.saveStand(stand);
    }

    public @Nullable Stand findStandById(@NonNull Long id) {
        return storageRepository.findStandById(id);
    }

    public @Nullable Stand updateStand(@NonNull Long id, @NonNull Stand model) {
        var existing = storageRepository.findStandById(id);
        if (existing == null) {
            return null;
        }

        Long locationId = model.locationId() != null ? model.locationId() : existing.locationId();
        if (locationId != null) {
            locationRepository.findLocationEntityById(locationId);
        }

        var updated = new Stand(id, model.name(), locationId);
        return storageRepository.saveStand(updated);
    }

    public @NonNull List<Stand> findAllStands(@NonNull Pageable pageable) {
        return storageRepository.findAllStands(pageable);
    }

    public void deleteStandById(@NonNull Long id) {
        storageRepository.deleteStandById(id);
    }

    // RelayCabinet operations
    public RelayCabinet saveRelayCabinet(@NonNull RelayCabinet model, @NonNull Long locationId) {
        locationRepository.findLocationEntityById(locationId);

        var cabinet = new RelayCabinet(null, model.name(), locationId);
        return storageRepository.saveRelayCabinet(cabinet);
    }

    public @Nullable RelayCabinet findRelayCabinetById(@NonNull Long id) {
        return storageRepository.findRelayCabinetById(id);
    }

    public @Nullable RelayCabinet updateRelayCabinet(@NonNull Long id, @NonNull RelayCabinet model) {
        var existing = storageRepository.findRelayCabinetById(id);
        if (existing == null) {
            return null;
        }

        Long locationId = model.locationId() != null ? model.locationId() : existing.locationId();
        if (locationId != null) {
            locationRepository.findLocationEntityById(locationId);
        }

        var updated = new RelayCabinet(id, model.name(), locationId);
        return storageRepository.saveRelayCabinet(updated);
    }

    public @NonNull List<RelayCabinet> findAllRelayCabinets(@NonNull Pageable pageable) {
        return storageRepository.findAllRelayCabinets(pageable);
    }

    public void deleteRelayCabinetById(@NonNull Long id) {
        storageRepository.deleteRelayCabinetById(id);
    }

    // Location finder methods
    public @Nullable Station findStationById(@NonNull Long id) {
        return locationRepository.findStationById(id);
    }

    public @Nullable TrackPoint findTrackPointById(@NonNull Long id) {
        return locationRepository.findTrackPointById(id);
    }

    public @Nullable Crossing findCrossingById(@NonNull Long id) {
        return locationRepository.findCrossingById(id);
    }
}
