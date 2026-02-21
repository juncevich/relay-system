package com.relay.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record UpdateRelayRequest(
        @NotBlank @Size(min = 5, max = 10, message = "Serial number must be between 5 and 10 characters") String serialNumber,
        OffsetDateTime dateOfManufacture,
        OffsetDateTime verificationDate,
        Long storageId
) {
}
