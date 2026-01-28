package com.relay.web.dto.storage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStandRequest(
        @NotBlank String name,
        @NotNull Long locationId
) {
}
