package com.relay.db.entity.items;

import com.relay.db.entity.location.Location;
import com.relay.db.entity.place.Place;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Relay {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne()
    private Place place;

    @OneToOne
    private Location location;

    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private RelayType relayType;
}
