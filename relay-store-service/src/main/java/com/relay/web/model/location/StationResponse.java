package com.relay.web.model.location;

import lombok.Builder;

@Builder
public record StationResponse(
        Long id,
        String name
) {
}
