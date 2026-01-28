package com.relay.core.service;

import com.relay.core.model.location.CrossingModel;
import com.relay.core.model.location.StationModel;
import com.relay.core.model.location.TrackPointModel;
import com.relay.db.entity.location.Crossing;
import com.relay.db.entity.location.Station;
import com.relay.db.entity.location.TrackPoint;
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
    public StationModel saveStation(@NonNull StationModel model) {
        Station entity = locationMapper.mapModelToStationEntity(model);
        Station saved = stationRepository.save(entity);
        return locationMapper.mapStationEntityToModel(saved);
    }

    public @Nullable StationModel findStationById(@NonNull Long id) {
        return stationRepository.findById(id)
                .map(locationMapper::mapStationEntityToModel)
                .orElse(null);
    }

    public @Nullable StationModel updateStation(@NonNull Long id, @NonNull StationModel model) {
        return stationRepository.findById(id)
                .map(station -> {
                    station.setName(model.name());
                    return locationMapper.mapStationEntityToModel(stationRepository.save(station));
                })
                .orElse(null);
    }

    public @NonNull List<StationModel> findAllStations(@NonNull Pageable pageable) {
        var slice = stationRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(locationMapper::mapStationEntityToModel).toList()
                : List.of();
    }

    public void deleteStationById(@NonNull Long id) {
        stationRepository.deleteById(id);
    }

    // TrackPoint operations
    public TrackPointModel saveTrackPoint(@NonNull TrackPointModel model) {
        TrackPoint entity = locationMapper.mapModelToTrackPointEntity(model);
        TrackPoint saved = trackPointRepository.save(entity);
        return locationMapper.mapTrackPointEntityToModel(saved);
    }

    public @Nullable TrackPointModel findTrackPointById(@NonNull Long id) {
        return trackPointRepository.findById(id)
                .map(locationMapper::mapTrackPointEntityToModel)
                .orElse(null);
    }

    public @Nullable TrackPointModel updateTrackPoint(@NonNull Long id, @NonNull TrackPointModel model) {
        return trackPointRepository.findById(id)
                .map(trackPoint -> {
                    trackPoint.setName(model.name());
                    return locationMapper.mapTrackPointEntityToModel(trackPointRepository.save(trackPoint));
                })
                .orElse(null);
    }

    public @NonNull List<TrackPointModel> findAllTrackPoints(@NonNull Pageable pageable) {
        var slice = trackPointRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(locationMapper::mapTrackPointEntityToModel).toList()
                : List.of();
    }

    public void deleteTrackPointById(@NonNull Long id) {
        trackPointRepository.deleteById(id);
    }

    // Crossing operations
    public CrossingModel saveCrossing(@NonNull CrossingModel model) {
        Crossing entity = locationMapper.mapModelToCrossingEntity(model);
        Crossing saved = crossingRepository.save(entity);
        return locationMapper.mapCrossingEntityToModel(saved);
    }

    public @Nullable CrossingModel findCrossingById(@NonNull Long id) {
        return crossingRepository.findById(id)
                .map(locationMapper::mapCrossingEntityToModel)
                .orElse(null);
    }

    public @Nullable CrossingModel updateCrossing(@NonNull Long id, @NonNull CrossingModel model) {
        return crossingRepository.findById(id)
                .map(crossing -> {
                    crossing.setName(model.name());
                    return locationMapper.mapCrossingEntityToModel(crossingRepository.save(crossing));
                })
                .orElse(null);
    }

    public @NonNull List<CrossingModel> findAllCrossings(@NonNull Pageable pageable) {
        var slice = crossingRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(locationMapper::mapCrossingEntityToModel).toList()
                : List.of();
    }

    public void deleteCrossingById(@NonNull Long id) {
        crossingRepository.deleteById(id);
    }
}
