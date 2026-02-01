package com.relay.web.model.storage;

import lombok.Builder;

@Builder
public record ShelfResponse(
        Long id,
        int number,
        int capacity,
        Long storageId
) {
}
