package com.relay.web.dto.history;

import com.relay.web.model.history.RelayMovementResponse;

import java.util.List;

public record GetAllRelayMovementsResponse(
        List<RelayMovementResponse> relayMovements,
        long totalElements,
        int totalPages,
        int size,
        int number
) {
}
