package com.relay.web.dto.storage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRelayCabinetRequest(
        @NotBlank String name,
        @NotNull Long locationId
) {
}
