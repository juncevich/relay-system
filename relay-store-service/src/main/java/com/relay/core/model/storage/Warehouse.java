package com.relay.core.model.storage;

import lombok.Builder;

@Builder
public record Warehouse(
        Long id,
        String name,
        Long locationId
) {
}
