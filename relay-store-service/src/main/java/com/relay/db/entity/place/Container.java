package com.relay.db.entity.place;

import com.relay.db.entity.items.Relay;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Container {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @OneToMany
    private List<Relay> relays;
}
