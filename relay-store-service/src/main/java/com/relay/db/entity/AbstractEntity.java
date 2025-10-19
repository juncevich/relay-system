package com.relay.db.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class AbstractEntity {
    @GeneratedValue(generator = "snowFlakeId")
//    @GenericGenerator(name = "snowFlakeId", strategy = "ru.relay.infrastructure.db.SnowflakeIdGenerator")
    Long id;

}
