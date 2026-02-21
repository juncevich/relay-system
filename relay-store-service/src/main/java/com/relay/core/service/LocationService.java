package com.relay.core.service;

import com.relay.core.exceptions.LocationNotFoundException;
import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.db.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LocationService {

    private final LocationRepository locationRepository;

    // Station operations
    public Station saveStation(@NonNull Station model) {
        return locationRepository.saveStation(model);
    }

    @Transactional(readOnly = true)
    public Optional<Station> findStationById(@NonNull Long id) {
        return locationRepository.findStationById(id);
    }

    public Station updateStation(@NonNull Long id, @NonNull Station model) {
        var existing = locationRepository.findStationById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        var updated = new Station(id, model.name());
        return locationRepository.saveStation(updated);
    }

    @Transactional(readOnly = true)
    public @NonNull Page<Station> findAllStations(@NonNull Pageable pageable) {
        return locationRepository.findAllStations(pageable);
    }

    public void deleteStationById(@NonNull Long id) {
        locationRepository.deleteStationById(id);
    }

    // TrackPoint operations
    public TrackPoint saveTrackPoint(@NonNull TrackPoint model) {
        return locationRepository.saveTrackPoint(model);
    }

    @Transactional(readOnly = true)
    public Optional<TrackPoint> findTrackPointById(@NonNull Long id) {
        return locationRepository.findTrackPointById(id);
    }

    public TrackPoint updateTrackPoint(@NonNull Long id, @NonNull TrackPoint model) {
        var existing = locationRepository.findTrackPointById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        var updated = new TrackPoint(id, model.name());
        return locationRepository.saveTrackPoint(updated);
    }

    @Transactional(readOnly = true)
    public @NonNull Page<TrackPoint> findAllTrackPoints(@NonNull Pageable pageable) {
        return locationRepository.findAllTrackPoints(pageable);
    }

    public void deleteTrackPointById(@NonNull Long id) {
        locationRepository.deleteTrackPointById(id);
    }

    // Crossing operations
    public Crossing saveCrossing(@NonNull Crossing model) {
        return locationRepository.saveCrossing(model);
    }

    @Transactional(readOnly = true)
    public Optional<Crossing> findCrossingById(@NonNull Long id) {
        return locationRepository.findCrossingById(id);
    }

    public Crossing updateCrossing(@NonNull Long id, @NonNull Crossing model) {
        var existing = locationRepository.findCrossingById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        var updated = new Crossing(id, model.name());
        return locationRepository.saveCrossing(updated);
    }

    @Transactional(readOnly = true)
    public @NonNull Page<Crossing> findAllCrossings(@NonNull Pageable pageable) {
        return locationRepository.findAllCrossings(pageable);
    }

    public void deleteCrossingById(@NonNull Long id) {
        locationRepository.deleteCrossingById(id);
    }
}
