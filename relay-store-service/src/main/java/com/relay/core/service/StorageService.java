package com.relay.core.service;

import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.core.model.storage.RelayCabinet;
import com.relay.core.model.storage.Stand;
import com.relay.core.model.storage.Warehouse;
import com.relay.db.repository.LocationRepository;
import com.relay.db.repository.StorageRepository;
import com.relay.web.exceptions.StorageNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Warehouse> findWarehouseById(@NonNull Long id) {
        return storageRepository.findWarehouseById(id);
    }

    public Warehouse updateWarehouse(@NonNull Long id, @NonNull Warehouse model) {
        var existing = storageRepository.findWarehouseById(id)
                .orElseThrow(() -> new StorageNotFoundException(id));

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

    public Optional<Stand> findStandById(@NonNull Long id) {
        return storageRepository.findStandById(id);
    }

    public Stand updateStand(@NonNull Long id, @NonNull Stand model) {
        var existing = storageRepository.findStandById(id)
                .orElseThrow(() -> new StorageNotFoundException(id));

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

    public Optional<RelayCabinet> findRelayCabinetById(@NonNull Long id) {
        return storageRepository.findRelayCabinetById(id);
    }

    public RelayCabinet updateRelayCabinet(@NonNull Long id, @NonNull RelayCabinet model) {
        var existing = storageRepository.findRelayCabinetById(id)
                .orElseThrow(() -> new StorageNotFoundException(id));

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
    public Optional<Station> findStationById(@NonNull Long id) {
        return locationRepository.findStationById(id);
    }

    public Optional<TrackPoint> findTrackPointById(@NonNull Long id) {
        return locationRepository.findTrackPointById(id);
    }

    public Optional<Crossing> findCrossingById(@NonNull Long id) {
        return locationRepository.findCrossingById(id);
    }
}
