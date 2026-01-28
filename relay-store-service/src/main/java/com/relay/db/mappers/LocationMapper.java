package com.relay.db.mappers;

import com.relay.core.model.location.CrossingModel;
import com.relay.core.model.location.StationModel;
import com.relay.core.model.location.TrackPointModel;
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

    StationModel mapStationEntityToModel(Station entity);

    List<StationModel> mapStationEntityToModel(List<Station> entities);

    Station mapModelToStationEntity(StationModel model);

    TrackPointModel mapTrackPointEntityToModel(TrackPoint entity);

    List<TrackPointModel> mapTrackPointEntityToModel(List<TrackPoint> entities);

    TrackPoint mapModelToTrackPointEntity(TrackPointModel model);

    CrossingModel mapCrossingEntityToModel(Crossing entity);

    List<CrossingModel> mapCrossingEntityToModel(List<Crossing> entities);

    Crossing mapModelToCrossingEntity(CrossingModel model);
}
