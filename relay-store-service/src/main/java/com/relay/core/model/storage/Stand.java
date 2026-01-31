package com.relay.core.model.storage;

import lombok.Builder;

@Builder
public record Stand(
        Long id,
        String name,
        Long locationId
) {
}
