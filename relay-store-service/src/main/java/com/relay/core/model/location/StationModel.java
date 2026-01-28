package com.relay.core.model.location;

import lombok.Builder;

@Builder
public record StationModel(
        Long id,
        String name
) {
}
