package com.relay.core.model.location;

import lombok.Builder;

@Builder
public record Station(
        Long id,
        String name
) {
}
