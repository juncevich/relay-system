package com.relay.web.dto.history;

import jakarta.validation.constraints.NotNull;

public record CreateRelayMovementRequest(
        @NotNull Long relayId,
        @NotNull Long fromStorageId,
        @NotNull Long toStorageId
) {
}
