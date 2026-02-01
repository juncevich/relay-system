package com.relay.web.dto.location;

import com.relay.web.model.location.StationResponse;

import java.util.List;

public record GetAllStationsResponse(List<StationResponse> stations) {
}
