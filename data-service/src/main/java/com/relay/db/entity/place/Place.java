package com.relay.db.entity.place;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Place {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlaceType placeType;
}
