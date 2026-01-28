package com.relay.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record UpdateRelayRequest(
        @NotBlank @Size(min = 5) String serialNumber,
        OffsetDateTime dateOfManufacture,
        OffsetDateTime verificationDate,
        Long storageId
) {
}
