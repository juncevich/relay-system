package com.relay.core.model.storage;

import lombok.Builder;

@Builder
public record RelayCabinetModel(
        Long id,
        String name,
        Long locationId
) {
}
