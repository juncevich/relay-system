package com.relay.web.dto;

import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.time.OffsetDateTime;

public record CreateRelayRequest(
        @NonNull
        @Size(
                min = 5,
                max = 10,
                message = "Serial number should contain at least five characters"
        )
        String serialNumber,
        OffsetDateTime dateOfManufacture
//        OffsetDateTime verificationDate
) {
}
