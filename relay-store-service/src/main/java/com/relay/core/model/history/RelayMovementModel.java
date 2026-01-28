package com.relay.core.model.history;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record RelayMovementModel(
        Long id,
        Long relayId,
        String relaySerialNumber,
        Long fromStorageId,
        Long toStorageId,
        OffsetDateTime movedAt
) {
}
