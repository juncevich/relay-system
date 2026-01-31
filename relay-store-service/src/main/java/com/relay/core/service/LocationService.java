package com.relay.core.service;

import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.db.mappers.LocationMapper;
import com.relay.db.repository.CrossingRepository;
import com.relay.db.repository.StationRepository;
import com.relay.db.repository.TrackPointRepository;
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
public class LocationService {

    private final StationRepository stationRepository;
    private final TrackPointRepository trackPointRepository;
    private final CrossingRepository crossingRepository;
    private final LocationMapper locationMapper;

    // Station operations
    public Station saveStation(@NonNull Station model) {
        com.relay.db.entity.location.Station entity = locationMapper.mapModelToStationEntity(model);
        com.relay.db.entity.location.Station saved = stationRepository.save(entity);
        return locationMapper.mapStationEntityToModel(saved);
    }

    public @Nullable Station findStationById(@NonNull Long id) {
        return stationRepository.findById(id)
                .map(locationMapper::mapStationEntityToModel)
                .orElse(null);
    }

    public @Nullable Station updateStation(@NonNull Long id, @NonNull Station model) {
        return stationRepository.findById(id)
                .map(station -> {
                    station.setName(model.name());
                    return locationMapper.mapStationEntityToModel(stationRepository.save(station));
                })
                .orElse(null);
    }

    public @NonNull List<Station> findAllStations(@NonNull Pageable pageable) {
        var slice = stationRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(locationMapper::mapStationEntityToModel).toList()
                : List.of();
    }

    public void deleteStationById(@NonNull Long id) {
        stationRepository.deleteById(id);
    }

    // TrackPoint operations
    public TrackPoint saveTrackPoint(@NonNull TrackPoint model) {
        com.relay.db.entity.location.TrackPoint entity = locationMapper.mapModelToTrackPointEntity(model);
        com.relay.db.entity.location.TrackPoint saved = trackPointRepository.save(entity);
        return locationMapper.mapTrackPointEntityToModel(saved);
    }

    public @Nullable TrackPoint findTrackPointById(@NonNull Long id) {
        return trackPointRepository.findById(id)
                .map(locationMapper::mapTrackPointEntityToModel)
                .orElse(null);
    }

    public @Nullable TrackPoint updateTrackPoint(@NonNull Long id, @NonNull TrackPoint model) {
        return trackPointRepository.findById(id)
                .map(trackPoint -> {
                    trackPoint.setName(model.name());
                    return locationMapper.mapTrackPointEntityToModel(trackPointRepository.save(trackPoint));
                })
                .orElse(null);
    }

    public @NonNull List<TrackPoint> findAllTrackPoints(@NonNull Pageable pageable) {
        var slice = trackPointRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(locationMapper::mapTrackPointEntityToModel).toList()
                : List.of();
    }

    public void deleteTrackPointById(@NonNull Long id) {
        trackPointRepository.deleteById(id);
    }

    // Crossing operations
    public Crossing saveCrossing(@NonNull Crossing model) {
        com.relay.db.entity.location.Crossing entity = locationMapper.mapModelToCrossingEntity(model);
        com.relay.db.entity.location.Crossing saved = crossingRepository.save(entity);
        return locationMapper.mapCrossingEntityToModel(saved);
    }

    public @Nullable Crossing findCrossingById(@NonNull Long id) {
        return crossingRepository.findById(id)
                .map(locationMapper::mapCrossingEntityToModel)
                .orElse(null);
    }

    public @Nullable Crossing updateCrossing(@NonNull Long id, @NonNull Crossing model) {
        return crossingRepository.findById(id)
                .map(crossing -> {
                    crossing.setName(model.name());
                    return locationMapper.mapCrossingEntityToModel(crossingRepository.save(crossing));
                })
                .orElse(null);
    }

    public @NonNull List<Crossing> findAllCrossings(@NonNull Pageable pageable) {
        var slice = crossingRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(locationMapper::mapCrossingEntityToModel).toList()
                : List.of();
    }

    public void deleteCrossingById(@NonNull Long id) {
        crossingRepository.deleteById(id);
    }
}
