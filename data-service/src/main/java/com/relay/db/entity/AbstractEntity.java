package com.relay.db.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class AbstractEntity {
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.relay.common.SnowflakeId")
    Long id;

}
