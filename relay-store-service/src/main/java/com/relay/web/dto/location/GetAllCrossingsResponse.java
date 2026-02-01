package com.relay.web.dto.location;

import com.relay.web.model.location.CrossingResponse;

import java.util.List;

public record GetAllCrossingsResponse(List<CrossingResponse> crossings) {
}
