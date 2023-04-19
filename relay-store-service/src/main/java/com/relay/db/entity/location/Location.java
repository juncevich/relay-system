package com.relay.db.entity.location;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private LocationType type;
}
