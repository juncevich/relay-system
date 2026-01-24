package com.relay.db.entity.storage;

import com.relay.db.entity.location.Location;
import com.relay.infrastructure.db.annotation.EntityId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class Storage {

    @Id
    @EntityId
    @GeneratedValue(generator = "snowFlakeId")
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    private Location location;
}