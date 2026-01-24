package com.relay.db.entity.location;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class Location {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
