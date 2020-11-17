package com.relay.db.entity.items;

import com.relay.db.entity.place.Container;
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

    @OneToOne
    private Container container;

    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private RelayType relayType;
}
