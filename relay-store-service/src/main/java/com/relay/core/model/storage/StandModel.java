package com.relay.core.model.storage;

import lombok.Builder;

@Builder
public record StandModel(
        Long id,
        String name,
        Long locationId
) {
}
