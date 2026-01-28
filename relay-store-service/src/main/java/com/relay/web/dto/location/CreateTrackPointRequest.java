package com.relay.web.dto.location;

import jakarta.validation.constraints.NotBlank;

public record CreateTrackPointRequest(
        @NotBlank String name
) {
}
