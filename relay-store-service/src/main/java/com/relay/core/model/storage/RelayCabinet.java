package com.relay.core.model.storage;

import lombok.Builder;

@Builder
public record RelayCabinet(
        Long id,
        String name,
        Long locationId
) {
}
