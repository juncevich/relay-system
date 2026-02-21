package com.relay.web.dto.storage;

import com.relay.web.model.storage.ShelfResponse;

import java.util.List;

public record GetAllShelvesResponse(
        List<ShelfResponse> shelves,
        long totalElements,
        int totalPages,
        int size,
        int number
) {
}
