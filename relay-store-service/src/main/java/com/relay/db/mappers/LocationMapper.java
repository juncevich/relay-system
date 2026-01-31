package com.relay.db.mappers;

import com.relay.db.entity.location.Crossing;
import com.relay.db.entity.location.Station;
import com.relay.db.entity.location.TrackPoint;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationMapper {

    com.relay.core.model.location.Station mapStationEntityToModel(Station entity);

    List<com.relay.core.model.location.Station> mapStationEntityToModel(List<Station> entities);

    Station mapModelToStationEntity(com.relay.core.model.location.Station model);

    com.relay.core.model.location.TrackPoint mapTrackPointEntityToModel(TrackPoint entity);

    List<com.relay.core.model.location.TrackPoint> mapTrackPointEntityToModel(List<TrackPoint> entities);

    TrackPoint mapModelToTrackPointEntity(com.relay.core.model.location.TrackPoint model);

    com.relay.core.model.location.Crossing mapCrossingEntityToModel(Crossing entity);

    List<com.relay.core.model.location.Crossing> mapCrossingEntityToModel(List<Crossing> entities);

    Crossing mapModelToCrossingEntity(com.relay.core.model.location.Crossing model);
}
