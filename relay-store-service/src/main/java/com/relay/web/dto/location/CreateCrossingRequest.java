package com.relay.web.dto.location;

import jakarta.validation.constraints.NotBlank;

public record CreateCrossingRequest(
        @NotBlank String name
) {
}
