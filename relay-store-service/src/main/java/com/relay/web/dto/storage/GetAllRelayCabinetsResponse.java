package com.relay.web.dto.storage;

import com.relay.web.model.storage.RelayCabinetResponse;

import java.util.List;

public record GetAllRelayCabinetsResponse(
        List<RelayCabinetResponse> relayCabinets,
        long totalElements,
        int totalPages,
        int size,
        int number
) {
}
