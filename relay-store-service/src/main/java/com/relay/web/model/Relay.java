package com.relay.web.model;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Relay {

    OffsetDateTime createdAt;
    OffsetDateTime verificationDate;
    @NonNull
    @Size(min = 5, message = "Serial number should contain at least five characters")
    String         serialNumber;
}
