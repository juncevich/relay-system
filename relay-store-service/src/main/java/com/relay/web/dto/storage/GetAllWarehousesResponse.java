package com.relay.web.dto.storage;

import com.relay.web.model.storage.WarehouseResponse;

import java.util.List;

public record GetAllWarehousesResponse(
        List<WarehouseResponse> warehouses,
        long totalElements,
        int totalPages,
        int size,
        int number
) {
}
