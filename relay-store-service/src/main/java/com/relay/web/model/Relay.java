package com.relay.web.model;

import com.relay.db.entity.items.RelayType;

import java.time.OffsetDateTime;

public record Relay(
        Long id,
        String serialNumber,
        RelayType relayType,
        OffsetDateTime createdAt,
        OffsetDateTime verificationDate,
        int placeNumber,
        Long storageId,
        Long shelfId
) {
}
