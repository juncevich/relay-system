package com.relay.web.model.storage;

import lombok.Builder;

@Builder
public record WarehouseResponse(
        Long id,
        String name,
        Long locationId
) {
}
