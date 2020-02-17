package com.relay.db.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Shelve {

    @Id
    @GeneratedValue
    Long id;

    @OneToMany(mappedBy = "shelve")
    List<RelayEntity> relays;
}
