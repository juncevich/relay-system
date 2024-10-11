package com.relay.core.model;

import java.time.OffsetDateTime;

public record Relay(
        String serialNumber,
        OffsetDateTime dateOfManufacture,
        OffsetDateTime verificationDate
) {
}
