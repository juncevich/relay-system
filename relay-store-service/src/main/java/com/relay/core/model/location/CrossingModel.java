package com.relay.core.model.location;

import lombok.Builder;

@Builder
public record CrossingModel(
        Long id,
        String name
) {
}
