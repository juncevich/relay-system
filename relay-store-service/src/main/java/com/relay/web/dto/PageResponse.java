package com.relay.web.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> items,
        long totalElements,
        int totalPages,
        int size,
        int number
) {
}
