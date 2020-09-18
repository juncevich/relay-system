package com.relay.db.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class AbstractEntity {
    @Id
    @GeneratedValue
    Long id;

}
