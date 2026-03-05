package com.relay.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue;

import java.time.OffsetDateTime;

public record CreateRelayRequest(
        @NotBlank(message = "Serial number must not be blank")
        @Size(
                min = 5,
                max = 10,
                message = "Serial number must be between 5 and 10 characters"
        )
        String serialNumber,
        OffsetDateTime createdAt,
        OffsetDateTime dateOfManufacture,
        @NotNull
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
