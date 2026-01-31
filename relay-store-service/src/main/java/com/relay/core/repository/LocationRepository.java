package com.relay.core.repository;

import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.db.dao.CrossingDao;
import com.relay.db.dao.StationDao;
import com.relay.db.dao.TrackPointDao;
import com.relay.db.mappers.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocationRepository {

    private final StationDao stationDao;
    private final TrackPointDao trackPointDao;
    private final CrossingDao crossingDao;
    private final LocationMapper locationMapper;

    // Station operations
    public Station saveStation(@NonNull Station model) {
        var entity = locationMapper.mapModelToStationEntity(model);
        var saved = stationDao.save(entity);
        return locationMapper.mapStationEntityToModel(saved);
    }

    public @Nullable Station findStationById(@NonNull Long id) {
        return stationDao.findById(id)
                .map(locationMapper::mapStationEntityToModel)
                .orElse(null);
    }

    public @NonNull List<Station> findAllStations(@NonNull Pageable pageable) {
        var slice = stationDao.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream()
                .map(locationMapper::mapStationEntityToModel).toList()
                : List.of();
    }

    public void deleteStationById(@NonNull Long id) {
        stationDao.deleteById(id);
    }

    // TrackPoint operations
    public TrackPoint saveTrackPoint(@NonNull TrackPoint model) {
        var entity = locationMapper.mapModelToTrackPointEntity(model);
        var saved = trackPointDao.save(entity);
        return locationMapper.mapTrackPointEntityToModel(saved);
    }

    public @Nullable TrackPoint findTrackPointById(@NonNull Long id) {
        return trackPointDao.findById(id)
                .map(locationMapper::mapTrackPointEntityToModel)
                .orElse(null);
    }

    public @NonNull List<TrackPoint> findAllTrackPoints(@NonNull Pageable pageable) {
        var slice = trackPointDao.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream()
                .map(locationMapper::mapTrackPointEntityToModel).toList()
                : List.of();
    }

    public void deleteTrackPointById(@NonNull Long id) {
        trackPointDao.deleteById(id);
    }

    // Crossing operations
    public Crossing saveCrossing(@NonNull Crossing model) {
        var entity = locationMapper.mapModelToCrossingEntity(model);
        var saved = crossingDao.save(entity);
        return locationMapper.mapCrossingEntityToModel(saved);
    }

    public @Nullable Crossing findCrossingById(@NonNull Long id) {
        return crossingDao.findById(id)
                .map(locationMapper::mapCrossingEntityToModel)
                .orElse(null);
    }

    public @NonNull List<Crossing> findAllCrossings(@NonNull Pageable pageable) {
        var slice = crossingDao.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream()
                .map(locationMapper::mapCrossingEntityToModel).toList()
                : List.of();
    }

    public void deleteCrossingById(@NonNull Long id) {
        crossingDao.deleteById(id);
    }

    // Polymorphic finder - returns entity for relationship wiring
    public com.relay.db.entity.location.@NonNull Location findLocationEntityById(@NonNull Long locationId) {
        return stationDao.findById(locationId).map(l -> (com.relay.db.entity.location.Location) l)
                .or(() -> trackPointDao.findById(locationId).map(l -> (com.relay.db.entity.location.Location) l))
                .or(() -> crossingDao.findById(locationId).map(l -> (com.relay.db.entity.location.Location) l))
                .orElseThrow(() -> new IllegalArgumentException("Location not found: " + locationId));
    }
}
