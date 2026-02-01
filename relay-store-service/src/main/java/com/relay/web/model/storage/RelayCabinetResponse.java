package com.relay.web.model.storage;

import lombok.Builder;

@Builder
public record RelayCabinetResponse(
        Long id,
        String name,
        Long locationId
) {
}
