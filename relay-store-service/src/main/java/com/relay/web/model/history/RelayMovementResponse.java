package com.relay.web.model.history;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record RelayMovementResponse(
        Long id,
        Long relayId,
        String relaySerialNumber,
        Long fromStorageId,
        Long toStorageId,
        OffsetDateTime movedAt
) {
}
