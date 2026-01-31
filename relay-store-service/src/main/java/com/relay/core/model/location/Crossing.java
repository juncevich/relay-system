package com.relay.core.model.location;

import lombok.Builder;

@Builder
public record Crossing(
        Long id,
        String name
) {
}
