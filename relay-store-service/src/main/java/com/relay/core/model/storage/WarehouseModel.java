package com.relay.core.model.storage;

import lombok.Builder;

@Builder
public record WarehouseModel(
        Long id,
        String name,
        Long locationId
) {
}
