package com.relay.core.model.location;

import lombok.Builder;

@Builder
public record TrackPointModel(
        Long id,
        String name
) {
}
