package com.relaysystem.ms.relayservice;

import lombok.Data;
import lombok.NonNull;

@Data
public class RelayServiceConfiguration {
    @NonNull
    private String region;
}
