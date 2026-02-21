package com.relay.core.model;

import java.time.OffsetDateTime;

public record Relay(
        Long id,
        String serialNumber,
        RelayType relayType,
        OffsetDateTime createdAt,
        OffsetDateTime lastCheckDate,
        int placeNumber,
        Long storageId,
        Long shelfId
) {
}
