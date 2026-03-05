package com.relay.web.dto;

import java.time.OffsetDateTime;

public record CreateRelayResponse(
        String serialNumber,
        OffsetDateTime createdAt,
        OffsetDateTime dateOfManufacture,
        OffsetDateTime verificationDate
) {
}
