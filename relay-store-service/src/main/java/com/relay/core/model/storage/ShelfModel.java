package com.relay.core.model.storage;

import lombok.Builder;

@Builder
public record ShelfModel(
        Long id,
        int number,
        int capacity,
        Long storageId
) {
}
