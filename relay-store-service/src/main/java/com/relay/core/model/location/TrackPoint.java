package com.relay.core.model.location;

import lombok.Builder;

@Builder
public record TrackPoint(
        Long id,
        String name
) {
}
