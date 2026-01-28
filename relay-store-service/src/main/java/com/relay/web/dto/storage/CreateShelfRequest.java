package com.relay.web.dto.storage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateShelfRequest(
        @Min(1) int number,
        @Min(1) int capacity,
        @NotNull Long storageId
) {
}
