package com.relay.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record CreateRelayRequest(
        @NotBlank(message = "Serial number must not be blank")
        @Size(
                min = 5,
                max = 10,
                message = "Serial number must be between 5 and 10 characters"
        )
        String serialNumber,
        OffsetDateTime dateOfManufacture,
        @NotNull
        Long storageId
) {
}
