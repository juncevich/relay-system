package ru.relay.infrastructure.id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SnowflakeId {
    private Long raw;
}
