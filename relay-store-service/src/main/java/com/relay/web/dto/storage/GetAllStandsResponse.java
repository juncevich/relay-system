package com.relay.web.dto.storage;

import com.relay.web.model.storage.StandResponse;

import java.util.List;

public record GetAllStandsResponse(
        List<StandResponse> stands,
        long totalElements,
        int totalPages,
        int size,
        int number
) {
}
