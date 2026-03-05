package com.relay.db.entity.location;

import com.relay.infrastructure.db.annotation.EntityId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class Location {

    @Id
    @EntityId
    @GeneratedValue(generator = "snowFlakeId")
    private Long id;

    private String name;
}
