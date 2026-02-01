package com.relay.web.mappers;

import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.web.model.location.CrossingResponse;
import com.relay.web.model.location.StationResponse;
import com.relay.web.model.location.TrackPointResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationResponseMapper {

    StationResponse mapStationToResponse(Station model);

    List<StationResponse> mapStationsToResponse(List<Station> models);

    TrackPointResponse mapTrackPointToResponse(TrackPoint model);

    List<TrackPointResponse> mapTrackPointsToResponse(List<TrackPoint> models);

    CrossingResponse mapCrossingToResponse(Crossing model);

    List<CrossingResponse> mapCrossingsToResponse(List<Crossing> models);
}
