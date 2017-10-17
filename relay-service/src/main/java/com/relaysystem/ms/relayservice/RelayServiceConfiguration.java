package com.relaysystem.ms.relayservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class RelayServiceConfiguration {

    @NonNull
    private String region;
}
