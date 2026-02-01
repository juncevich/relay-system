package com.relay.web.dto.location;

import com.relay.web.model.location.TrackPointResponse;

import java.util.List;

public record GetAllTrackPointsResponse(List<TrackPointResponse> trackPoints) {
}
