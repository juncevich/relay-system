package com.relay.web.model.storage;

import lombok.Builder;

@Builder
public record StandResponse(
        Long id,
        String name,
        Long locationId
) {
}
