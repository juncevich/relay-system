package com.relay.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue;

import java.time.OffsetDateTime;

public record UpdateRelayRequest(
        @NotBlank @Size(min = 5, max = 10, message = "Serial number must be between 5 and 10 characters") String serialNumber,
        OffsetDateTime createdAt,
        OffsetDateTime dateOfManufacture,
        OffsetDateTime verificationDate,
        Long storageId
) {
    @AssertTrue(message = "createdAt and dateOfManufacture must be equal when both are provided")
    public boolean hasConsistentCreatedAt() {
        return createdAt == null
                || dateOfManufacture == null
                || createdAt.isEqual(dateOfManufacture);
    }

    public OffsetDateTime effectiveCreatedAt() {
        return createdAt != null ? createdAt : dateOfManufacture;
    }
}
