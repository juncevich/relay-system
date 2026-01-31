package com.relay.core.model.storage;

import lombok.Builder;

@Builder
public record Shelf(
        Long id,
        int number,
        int capacity,
        Long storageId
) {
}
