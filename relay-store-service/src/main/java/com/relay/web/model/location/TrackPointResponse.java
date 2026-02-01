package com.relay.web.model.location;

import lombok.Builder;

@Builder
public record TrackPointResponse(
        Long id,
        String name
) {
}
