package com.relay.web.model.location;

import lombok.Builder;

@Builder
public record CrossingResponse(
        Long id,
        String name
) {
}
